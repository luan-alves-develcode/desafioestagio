package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarrinhoService {

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    public void salvar(ItemCarrinho item) {
        itemCarrinhoRepository.save(item);
    }

    public List<ItemCarrinho> encontrarItensCarrinhoPorCarrinhoId(Long id) {
        return itemCarrinhoRepository.findAllByCarrinhoId(id);
    }
}
