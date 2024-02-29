package com.luan.desafio.desafioestagio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ItemParaoCarrinhoDto {
    @NotNull
    private Long produtoId;
    @NotNull
    private Integer quantidade;
}
