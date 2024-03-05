package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.model.ItemVenda;
import com.luan.desafio.desafioestagio.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaService {
    @Autowired
    ItemVendaRepository itemVendaRepository;

    public ItemVenda salvar(ItemVenda itemVenda) {
        return this.itemVendaRepository.save(itemVenda);
    }
}
