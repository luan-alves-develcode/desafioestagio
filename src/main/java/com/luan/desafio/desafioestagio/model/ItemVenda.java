package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "itens_venda")
public class ItemVenda {
    @EmbeddedId
    ItemVendaKey id = new ItemVendaKey();

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    Produto produto;

    @ManyToOne
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id")
    Venda venda;

    @Setter
    Integer quantidade;

    public ItemVenda(Produto produto, Venda venda, Integer quantidade) {
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
    }
}
