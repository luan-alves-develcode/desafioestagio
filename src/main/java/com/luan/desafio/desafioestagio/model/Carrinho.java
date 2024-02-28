package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@Table(name = "carrinhos")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total = BigDecimal.ZERO;
    private Integer quantidadeItens = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(
            name = "item_carrinho",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private final Map<Produto, Integer> produtos = new HashMap<Produto, Integer>();

    public void adicionarProduto(Produto produto) {
        produtos.put(produto, 1);
        total = total.add(produto.getPreco());
        quantidadeItens++;
    }
}
