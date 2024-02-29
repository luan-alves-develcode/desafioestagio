package com.luan.desafio.desafioestagio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CadastrarProdutoDto {
    @NotBlank
    private String nome;
    @NotBlank @Size(min = 10, message = "{validation.name.size.too_short}")
    @Size(max = 1000, message = "{validation.name.size.too_long}")
    private String descricao;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private Integer estoque;
}
