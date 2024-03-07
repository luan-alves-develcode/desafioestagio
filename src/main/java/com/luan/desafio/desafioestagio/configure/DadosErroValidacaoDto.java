package com.luan.desafio.desafioestagio.configure;

import lombok.Data;
import org.springframework.validation.FieldError;

@Data
public class DadosErroValidacaoDto {
    private String campo;
    private String mensagem;

    public DadosErroValidacaoDto(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }
}
