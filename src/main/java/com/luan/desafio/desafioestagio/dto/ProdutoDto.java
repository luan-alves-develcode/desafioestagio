package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoDto {
    @NotBlank
    private final String nome;
    @NotNull
    private final BigDecimal precoUnitario;

    public ProdutoDto(@NotNull Produto produto) {
        this.nome = produto.getNome();
        this.precoUnitario = produto.getPrecoUnitario();
    }
}
