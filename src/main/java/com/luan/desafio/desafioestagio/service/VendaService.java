package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.model.Venda;
import com.luan.desafio.desafioestagio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    @Autowired
    VendaRepository vendaRepository;

    public Venda salvar(Venda venda) {
        return this.vendaRepository.save(venda);
    }
}
