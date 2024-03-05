package com.luan.desafio.desafioestagio.model;

import com.luan.desafio.desafioestagio.dto.CadastrarProdutoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "produtos")
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;
    @NotNull
    private Integer estoque = 0;
    public Produto(String nome, String descricao, BigDecimal precoUnitario, Integer estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
    }

    public Produto(CadastrarProdutoDto dto) {
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.precoUnitario = dto.getPreco();
        this.estoque = dto.getEstoque();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + precoUnitario +
                ", estoque=" + estoque +
                '}';
    }
}
