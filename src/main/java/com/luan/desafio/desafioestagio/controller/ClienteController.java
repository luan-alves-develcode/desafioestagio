package com.luan.desafio.desafioestagio.controller;

import com.luan.desafio.desafioestagio.dto.CadastrarClienteDto;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarClienteDto dto) {
        try {
            Cliente cliente = clienteService.salvar(dto);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}