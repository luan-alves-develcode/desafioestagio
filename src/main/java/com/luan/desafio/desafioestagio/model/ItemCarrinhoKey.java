package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ItemCarrinhoKey implements Serializable {
    @Column(name = "produto_id")
    private Long produtoId;
    @Column(name = "carrinho_id")
    private Long carrinhoId;
}
