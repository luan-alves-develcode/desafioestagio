package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.CarrinhoDto;
import com.luan.desafio.desafioestagio.dto.ItemParaoCarrinhoDto;
import com.luan.desafio.desafioestagio.model.Carrinho;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.Produto;
import com.luan.desafio.desafioestagio.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

    public void criar(Long clienteId) {
        Cliente cliente = clienteService.findById(clienteId);
        Carrinho carrinho = new Carrinho(cliente);
        carrinhoRepository.save(carrinho);
    }

    public void adicionarItemAoCarrinho(ItemParaoCarrinhoDto itemParaCarrinhoDto, Long clienteId) {
        Integer quantidadeDoItem = itemParaCarrinhoDto.getQuantidade();
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        Produto produto = produtoService.findProdutoById(itemParaCarrinhoDto.getProdutoId());
        ItemCarrinho item = new ItemCarrinho(produto, carrinho, itemParaCarrinhoDto.getQuantidade());

        BigDecimal novoTotal = calcularTotal(carrinho.getTotal(), produto.getPreco(), quantidadeDoItem);
        carrinho.setTotal(novoTotal);
        carrinho.setQuantidadeItens(carrinho.getQuantidadeItens() + quantidadeDoItem);
        carrinho.getItensCarrinho().add(item);

        itemCarrinhoService.salvar(item);
    }

    public CarrinhoDto verCarrinho(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        List<ItemCarrinho> itensCarrinho = itemCarrinhoService.encontrarItensCarrinhoPorCarrinhoId(carrinho.getId());
        return new CarrinhoDto(carrinho, itensCarrinho);
    }

    public void apagar(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findCarrinhoByClienteId(clienteId);
        itemCarrinhoService.limparCarrinho(carrinho.getId());
        carrinho.setQuantidadeItens(0);
        carrinho.setTotal(BigDecimal.ZERO);
    }

    private BigDecimal calcularTotal(BigDecimal total,BigDecimal precoProduto, Integer quantidade) {
        return total.add(precoProduto.multiply(BigDecimal.valueOf(quantidade)));
    }

    private boolean verificaSeProdutoExisteNoCarrinho(Carrinho carrinho, Long produtoId) {
        Set<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();
        for (ItemCarrinho item: itensCarrinho) {
          if (item.getProduto().getId().equals(produtoId)) {
            return true;
          }
        }
        return false;
    }
}
