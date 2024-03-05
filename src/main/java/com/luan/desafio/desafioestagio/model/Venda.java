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
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private BigDecimal total;
    @Setter
    private Integer quantidadeItens;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY)
    private Set<ItemVenda> itensVenda = new HashSet<>();

    public Venda(Cliente cliente, BigDecimal total, Integer quantidadeItens, Set itensVenda) {
        this.cliente = cliente;
        this.total = total;
        this.quantidadeItens = quantidadeItens;
        this.itensVenda = itensVenda;
    }
}
