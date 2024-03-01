package com.luan.desafio.desafioestagio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ItemParaoCarrinhoDto {
    @NotNull
    private Long produtoId;
    @Nullable
    private Integer quantidade;
}
