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
        Cliente cliente = clienteService.encontrarPorId(clienteId);
        Carrinho carrinho = carrinhoRepository.save(new Carrinho(cliente));
    }

    public void adicionarItemAoCarrinho(ItemParaoCarrinhoDto itemParaCarrinhoDto, Long clienteId) {
        Integer quantidadeDoItem = 1;
        if (itemParaCarrinhoDto.getQuantidade() != null) {
            quantidadeDoItem = itemParaCarrinhoDto.getQuantidade();
        }
        Optional<Carrinho> optional = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Carrinho carrinho;
        if (optional.isPresent()) {
            carrinho = optional.get();
        } else {
            throw new ValidacaoException("Carrinho não encontrado para o cliente.");
        }

        carrinho.getItensCarrinho().forEach(item -> {
            if (item.getProduto().getId() == itemParaCarrinhoDto.getProdutoId()) {
                throw new ValidacaoException("Produto já existente no carrinho.");
            }
        });

        Produto produto = produtoService.findProdutoById(itemParaCarrinhoDto.getProdutoId());
        ItemCarrinho item = new ItemCarrinho(produto, carrinho, quantidadeDoItem);

        BigDecimal novoTotal = somaItemCarrinhoDoTotalCarrinho(carrinho.getTotal(), produto.getPrecoUnitario(), quantidadeDoItem);

        carrinho.setTotal(novoTotal);
        carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() + quantidadeDoItem);
        carrinho.getItensCarrinho().add(item);
        System.out.println(item);

        itemCarrinhoService.salvar(item);
    }

    public CarrinhoDto verCarrinho(Long clienteId) {
        Optional<Carrinho> optional = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Carrinho carrinho = optional.orElseThrow(() -> new ValidacaoException("Carrinho não encontrado para o usuário informado."));

        HashSet<ItemCarrinho> itensCarrinho = itemCarrinhoService.encontrarItensCarrinhoPorCarrinhoId(carrinho.getId());
        return new CarrinhoDto(carrinho, itensCarrinho);
    }

    public void apagar(Long clienteId) {
        Optional<Carrinho> optional = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Carrinho carrinho = optional.orElseThrow(() -> new ValidacaoException("Carrinho não encontrado para o usuário informado."));

        itemCarrinhoService.limparCarrinho(carrinho.getId());
        carrinho.setQuantidadeItens(0);
        carrinho.setTotal(BigDecimal.ZERO);
    }

    public void removerItem(Long clienteId, Long produtoId) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Carrinho carrinho = optionalCarrinho.orElseThrow(() -> new ValidacaoException("Carrinho não encontrado para o usuário informado."));
        Optional<ItemCarrinho> optional = itemCarrinhoService.encontrarPorCarrinhoIdEProdutoId(carrinho.getId(), produtoId);

        if (optional.isPresent()) {
            ItemCarrinho itemCarrinho = optional.get();

            atualizarValoresDoCarrinhoAposRemoverItem(carrinho, itemCarrinho);

            itemCarrinhoService.removerItemPorCarrinhoIdeProdutoId(carrinho.getId(), produtoId);
        } else {
            throw new ValidacaoException("Item não encontrado no carrinho.");
        }
    }

    public CarrinhoDto atualizar(Long clienteId, AtualizarCarrinhoDto atualizarCarrinhoDto) {
        Optional<Carrinho> optional = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Carrinho carrinho = optional.orElseThrow(() -> new ValidacaoException("Carrinho não encontrado para o usuário informado."));

        HashMap<Long, ItemCarrinho> mapItemCarrinho = new HashMap<>(carrinho.getItensCarrinho()
                .stream()
                .collect(Collectors.toMap(item -> item.getProduto().getId(), item -> item)));

        for (AtualizarItemCarrinhoDto dtoItemCarrinho : atualizarCarrinhoDto.getCarrinho()) {
            if (!mapItemCarrinho.containsKey(dtoItemCarrinho.getId())) {
                Produto produto = produtoService.findProdutoById(dtoItemCarrinho.getId());
                var item = new ItemCarrinho(produto, carrinho, dtoItemCarrinho.getQuantidade());
                itemCarrinhoService.salvar(item);
                mapItemCarrinho.put(dtoItemCarrinho.getId(), item);
            }
            ItemCarrinho item = mapItemCarrinho.get(dtoItemCarrinho.getId());
            item.setQuantidade(dtoItemCarrinho.getQuantidade());
            itemCarrinhoService.salvar(item);
            mapItemCarrinho.replace(dtoItemCarrinho.getId(), item);
        }

        calcularValoresDoCarrinhoESetar(carrinho);
        CarrinhoDto carrinhoDto = new CarrinhoDto(carrinho, carrinho.getItensCarrinho());

        return carrinhoDto;
    }

    public Venda finalizar(Long clienteId) {
        Cliente cliente = clienteService.encontrarPorId(clienteId);
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId).orElseThrow(() -> new ValidacaoException("Carrinho não encontrado."));

        if (carrinho.getItensCarrinho().isEmpty()) {
            throw new ValidacaoException("Carrinho vazio não pode ser finalizado.");
        }

        Venda novaVenda = new Venda(cliente, carrinho.getTotal(), carrinho.getQuantidadeItens());
        System.out.println(cliente.getId() + " " + carrinho.getId() + " " + carrinho.getItensCarrinho());
        System.out.println("venda_cliente_id: " + novaVenda.getCliente().getId() + " Qtd itens: " + novaVenda.getQuantidadeItens() + " Total: " + novaVenda.getTotal());
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

    private void calcularValoresDoCarrinhoESetar(Carrinho carrinho) {
        carrinho.setQuantidadeItens(carrinho.getItensCarrinho().stream()
                .mapToInt(ItemCarrinho::getQuantidade)
                .sum()
        );
        carrinho.setTotal(carrinho.getItensCarrinho()
                .stream()
                .map(item -> item.getProduto().getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    private void atualizarValoresDoCarrinhoAposRemoverItem(Carrinho carrinho, ItemCarrinho itemCarrinho) {
        carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() - itemCarrinho.getQuantidade());
        carrinho.setTotal(carrinho.getTotal().subtract(itemCarrinho.getProduto().getPrecoUnitario().multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade()))));
    }
}
