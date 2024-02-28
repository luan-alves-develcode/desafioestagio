package com.luan.desafio.desafioestagio.dto;

import com.luan.desafio.desafioestagio.model.Carrinho;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CadastrarClienteDto {
    @NotBlank
    private String nome;
    @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    private String cpf;
    @NotBlank @Email
    private String email;
    @NotNull
    private DadosEnderecoDto endereco;

    @Override
    public String toString() {
        return "CadastrarClienteDto{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
