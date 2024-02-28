package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.model.Carrinho;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {
    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ClienteService clienteService;

    public void criar(Long clienteId) {
        Cliente cliente = clienteService.findById(clienteId);
        Carrinho carrinho = new Carrinho(cliente);
        carrinhoRepository.save(carrinho);
    }
}
