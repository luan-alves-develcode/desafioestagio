package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.AtualizarCarrinhoDto;
import com.luan.desafio.desafioestagio.dto.AtualizarItemCarrinhoDto;
import com.luan.desafio.desafioestagio.dto.CarrinhoDto;
import com.luan.desafio.desafioestagio.dto.ItemParaoCarrinhoDto;
import com.luan.desafio.desafioestagio.exception.ValidacaoException;
import com.luan.desafio.desafioestagio.model.Carrinho;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.ItemVenda;
import com.luan.desafio.desafioestagio.model.Produto;
import com.luan.desafio.desafioestagio.model.Venda;
import com.luan.desafio.desafioestagio.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemCarrinhoService itemCarrinhoService;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ItemVendaService itemVendaService;

    public void criar(Long clienteId) {
        Cliente cliente = clienteService.findById(clienteId);
        Carrinho carrinho = new Carrinho(cliente);
        carrinhoRepository.save(carrinho);
    }

    public void adicionarItemAoCarrinho(ItemParaoCarrinhoDto itemParaCarrinhoDto, Long clienteId) {
        Integer quantidadeDoItem = 1;
        if (itemParaCarrinhoDto.getQuantidade() != null) {
            quantidadeDoItem = itemParaCarrinhoDto.getQuantidade();
        }
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Produto produto = produtoService.findProdutoById(itemParaCarrinhoDto.getProdutoId());
        ItemCarrinho item = new ItemCarrinho(produto, carrinho, quantidadeDoItem);

        BigDecimal novoTotal = somaItemCarrinhoDoTotalCarrinho(carrinho.getTotal(), produto.getPreco(), quantidadeDoItem);

        carrinho.setTotal(novoTotal);
        carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() + quantidadeDoItem);
        carrinho.getItensCarrinho().add(item);

        itemCarrinhoService.salvar(item);
    }

    public CarrinhoDto verCarrinho(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        HashSet<ItemCarrinho> itensCarrinho = itemCarrinhoService.encontrarItensCarrinhoPorCarrinhoId(carrinho.getId());
        return new CarrinhoDto(carrinho, itensCarrinho);
    }

    public void apagar(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        itemCarrinhoService.limparCarrinho(carrinho.getId());
        carrinho.setQuantidadeItens(0);
        carrinho.setTotal(BigDecimal.ZERO);
    }

    public void removerItem(Long clienteId, Long produtoId) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Optional<ItemCarrinho> optional = itemCarrinhoService.encontrarPorCarrinhoIdEProdutoId(carrinho.getId(), produtoId);

        if (optional.isPresent()) {
            ItemCarrinho itemCarrinho = optional.get();

            atualizarValoresDoCarrinhoAposRemoverItem(carrinho, itemCarrinho);

            itemCarrinhoService.removerItemPorCarrinhoIdeProdutoId(carrinho.getId(), produtoId);
        } else {
            throw new ValidacaoException("Item não encontrado.");
        }
    }

    public CarrinhoDto atualizar(Long clienteId, AtualizarCarrinhoDto atualizarCarrinhoDto) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);

        HashMap<Long, ItemCarrinho> mapItemCarrinho = new HashMap<>(carrinho.getItensCarrinho()
                .stream()
                .collect(Collectors.toMap(item -> item.getProduto().getId(), item -> item)));
        HashMap<Long, ItemCarrinho> diferencaItemCarrinho = new HashMap<>();

        for (AtualizarItemCarrinhoDto dtoItemCarrinho : atualizarCarrinhoDto.getCarrinho()) {
            if (mapItemCarrinho.containsKey(dtoItemCarrinho.getId())) {
                ItemCarrinho atualizadoItemCarrinho = mapItemCarrinho.get(dtoItemCarrinho.getId());
                Integer novaQuantidadeDeItens = dtoItemCarrinho.getQuantidade() - atualizadoItemCarrinho.getQuantidade();
                BigDecimal novoTotalDoCarrinho = somaItemCarrinhoDoTotalCarrinho(carrinho.getTotal(),
                        atualizadoItemCarrinho.getProduto().getPreco(),
                        novaQuantidadeDeItens);

                atualizadoItemCarrinho.setQuantidade(dtoItemCarrinho.getQuantidade());
                diferencaItemCarrinho.put(dtoItemCarrinho.getId(), atualizadoItemCarrinho);

                carrinho.setTotal(novoTotalDoCarrinho);
                carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() + (novaQuantidadeDeItens));
            }
        }

        for (ItemCarrinho itemCarrinho : diferencaItemCarrinho.values()) {
            itemCarrinhoService.salvar(itemCarrinho);
        }


        CarrinhoDto carrinhoDto = new CarrinhoDto(carrinhoRepository.findCarrinhoByClienteId(clienteId), carrinhoRepository.findCarrinhoByClienteId(clienteId).getItensCarrinho());

        return carrinhoDto;
    }

    public Venda finalizar(Long clienteId) {
        Cliente cliente = clienteService.findById(clienteId);
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);

        if (carrinho.getItensCarrinho().isEmpty()) {
            throw new ValidacaoException("Carrinho vazio não pode ser finalizado.");
        }

        Venda novaVenda = new Venda(cliente, carrinho.getTotal(), carrinho.getQuantidadeItens());
        System.out.println(cliente.getId() + " " + carrinho.getId() + " " + carrinho.getItensCarrinho());
        System.out.println("venda_cliente_id: " + novaVenda.getCliente().getId() + " Qtd itens: " + novaVenda.getQuantidadeItens() +  " Total: " + novaVenda.getTotal());
        Venda vendaSalva = vendaService.salvar(novaVenda);

        carrinho.getItensCarrinho().forEach(itemCarrinho -> {
            itemVendaService.salvar(new ItemVenda(itemCarrinho, vendaSalva));
        });

        this.apagar(clienteId);
        return vendaSalva;
    }

    private BigDecimal somaItemCarrinhoDoTotalCarrinho(BigDecimal total, BigDecimal precoProduto, Integer quantidade) {
        return total.add(precoProduto.multiply(BigDecimal.valueOf(quantidade)));
    }

    private boolean verificaSeProdutoExisteNoCarrinho(Carrinho carrinho, Long produtoId) {
        Set<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();
        for (ItemCarrinho item : itensCarrinho) {
            if (item.getProduto().getId().equals(produtoId)) {
                return true;
            }
        }
        return false;
    }

    private void atualizarValoresDoCarrinhoAposRemoverItem(Carrinho carrinho, ItemCarrinho itemCarrinho) {
        carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() - itemCarrinho.getQuantidade());
        carrinho.setTotal(carrinho.getTotal().subtract(itemCarrinho.getProduto().getPreco().multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade()))));
    }
}
