package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.Carrinho;
import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CarrinhoDto {
    private List<ItemCarrinhoDto> carrinho;
    private BigDecimal total;
    private Integer quantidadeItens;

    public CarrinhoDto(@NotNull Carrinho carrinho,@NotNull List<ItemCarrinho> itensCarrinho) {
        this.carrinho = itensCarrinho.stream().map(ItemCarrinhoDto::new).toList();
        this.total = carrinho.getTotal();
        this.quantidadeItens = carrinho.getQuantidadeItens();
    }
}
