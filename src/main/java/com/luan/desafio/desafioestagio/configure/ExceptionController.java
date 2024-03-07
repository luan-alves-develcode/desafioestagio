package com.luan.desafio.desafioestagio.configure;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentoNotFoundError(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacaoDto::new).toList());
    }
}
