package com.luan.desafio.desafioestagio.model;

import com.luan.desafio.desafioestagio.dto.DadosEnderecoDto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Table(name = "enderecos")
@NoArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEnderecoDto endereco) {
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }
}
