package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemCarrinhoDto {
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    public ItemCarrinhoDto(@NotNull ItemCarrinho itemCarrinho) {
        this.nome = itemCarrinho.getProduto().getNome();
        this.preco = itemCarrinho.getProduto().getPreco();
        this.quantidade = itemCarrinho.getQuantidade();
    }
}