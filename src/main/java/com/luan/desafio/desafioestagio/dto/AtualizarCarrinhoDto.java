package com.luan.desafio.desafioestagio.dto;

import jakarta.validation.Valid;
import lombok.Getter;

import java.util.List;

@Getter
public class AtualizarCarrinhoDto {
    @Valid
    List<AtualizarItemCarrinhoDto> carrinho;
}
