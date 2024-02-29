package com.luan.desafio.desafioestagio.controller;

import com.luan.desafio.desafioestagio.dto.ItemParaoCarrinhoDto;
import com.luan.desafio.desafioestagio.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<String> criar(@PathVariable Long clienteId) {
        try {
            carrinhoService.criar(clienteId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/{clienteId}/adicionar")
    @Transactional
    public ResponseEntity<String> adicionarItemAoCarrinho(@RequestBody ItemParaoCarrinhoDto itemParaCarrinhoDto, @PathVariable Long clienteId) {
        try {
            carrinhoService.adicionarItemAoCarrinho(itemParaCarrinhoDto, clienteId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
