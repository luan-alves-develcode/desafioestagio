package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_carrinho")
public class ItemCarrinho {
    @EmbeddedId
    ItemCarrinhoKey id = new ItemCarrinhoKey();

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    Produto produto;

    @ManyToOne
    @MapsId("carrinhoId")
    @JoinColumn(name = "carrinho_id")
    Carrinho carrinho;

    Integer quantidade;

    public ItemCarrinho(Produto produto, Carrinho carrinho, Integer quantidade) {
        this.produto = produto;
        this.carrinho = carrinho;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "produto=" + produto +
                ", carrinho_id=" + carrinho.getId() +
                ", quantidade=" + quantidade +
                '}';
    }
}

