package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AtualizarItemCarrinhoDto {
    private Long id;
    @Positive
    private Integer quantidade;

    public AtualizarItemCarrinhoDto(@NotNull ItemCarrinho itemCarrinho) {
        this.id = itemCarrinho.getProduto().getId();
        this.quantidade = itemCarrinho.getQuantidade();
    }
}
