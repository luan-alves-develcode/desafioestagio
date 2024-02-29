package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.ItemParaoCarrinhoDto;
import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCarrinhoService {

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    public void salvar(ItemCarrinho item) {
        itemCarrinhoRepository.save(item);
    }
}
