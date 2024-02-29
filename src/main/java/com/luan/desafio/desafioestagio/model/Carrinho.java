package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Entity
@Table(name = "carrinhos")
@NoArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private BigDecimal total = BigDecimal.ZERO;
    @Setter
    private Integer quantidadeItens = 0;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "carrinho", fetch = FetchType.LAZY)
    private Set<ItemCarrinho> itensCarrinho = new HashSet<>();

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", total=" + total +
                ", quantidadeItens=" + quantidadeItens +
                ", cliente=" + cliente +
                ", itensCarrinho=" + itensCarrinho +
                '}';
    }
}
