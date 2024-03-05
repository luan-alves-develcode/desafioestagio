package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
@Data
public class ItemVendaKey {
    @Column(name = "produto_id")
    private Long produtoId;
    @Column(name = "venda_id")
    private Long vendaId;
}
