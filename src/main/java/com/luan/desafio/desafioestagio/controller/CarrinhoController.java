package com.luan.desafio.desafioestagio.controller;

import com.luan.desafio.desafioestagio.dto.AtualizarCarrinhoDto;
import com.luan.desafio.desafioestagio.dto.CarrinhoDto;
import com.luan.desafio.desafioestagio.dto.ItemParaoCarrinhoDto;
import com.luan.desafio.desafioestagio.dto.ProdutoIdDto;
import com.luan.desafio.desafioestagio.model.Venda;
import com.luan.desafio.desafioestagio.service.CarrinhoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<String> criar(@PathVariable Long clienteId) {
        carrinhoService.criar(clienteId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clienteId}/finalizar")
    @Transactional
    public ResponseEntity<String> finalizar(@PathVariable Long clienteId) {
        Venda venda = carrinhoService.finalizar(clienteId);
        return ResponseEntity.created(URI.create("/vendas/" + venda.getId())).build();
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<CarrinhoDto> atualizar(@PathVariable Long clienteId, @RequestBody @Valid AtualizarCarrinhoDto atualizarCarrinhoDto) {
        return ResponseEntity.ok(carrinhoService.atualizar(clienteId, atualizarCarrinhoDto));
    }

    @PostMapping("/{clienteId}/adicionar")
    @Transactional
    public ResponseEntity<CarrinhoDto> adicionarItemAoCarrinho(@RequestBody @Valid ItemParaoCarrinhoDto itemParaCarrinhoDto, @PathVariable Long clienteId) {
        carrinhoService.adicionarItemAoCarrinho(itemParaCarrinhoDto, clienteId);
        return ResponseEntity.ok(carrinhoService.verCarrinho(clienteId));
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<CarrinhoDto> listar(@PathVariable Long clienteId) {
        return ResponseEntity.ok(carrinhoService.verCarrinho(clienteId));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<String> apagar(@PathVariable Long clienteId) {
        carrinhoService.apagar(clienteId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{clienteId}/remover")
    @Transactional
    public ResponseEntity<String> removerItem(@PathVariable Long clienteId, @RequestBody @Valid ProdutoIdDto produtoIdDto) {
        carrinhoService.removerItem(clienteId, produtoIdDto.getProdutoId());
        return ResponseEntity.ok().build();
    }
}
