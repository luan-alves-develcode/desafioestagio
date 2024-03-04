package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemCarrinhoDto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    @Positive
    private Integer quantidade;

    public ItemCarrinhoDto(@NotNull ItemCarrinho itemCarrinho) {
        this.id = itemCarrinho.getProduto().getId();
        this.nome = itemCarrinho.getProduto().getNome();
        this.preco = itemCarrinho.getProduto().getPreco();
        this.quantidade = itemCarrinho.getQuantidade();
    }
}
