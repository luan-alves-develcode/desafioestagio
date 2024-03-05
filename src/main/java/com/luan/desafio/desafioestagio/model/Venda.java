package com.luan.desafio.desafioestagio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    private Integer quantidadeItens;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY)
    private Set<ItemVenda> itensVenda = new HashSet<>();

    public Venda(Cliente cliente, BigDecimal total, Integer quantidadeItens) {
        this.cliente = cliente;
        this.total = total;
        this.quantidadeItens = quantidadeItens;
        this.itensVenda = itensVenda;
    }
}
