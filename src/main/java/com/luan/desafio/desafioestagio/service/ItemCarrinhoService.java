package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ItemCarrinhoService {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    public void salvar(ItemCarrinho item) {
        itemCarrinhoRepository.save(item);
    }

    public HashSet<ItemCarrinho> encontrarItensCarrinhoPorCarrinhoId(Long id) {
        return itemCarrinhoRepository.findAllByCarrinhoId(id);
    }

    public void limparCarrinho(Long carrinhoId) {
        itemCarrinhoRepository.deleteAllByCarrinhoId(carrinhoId);
    }

    public void removerItemPorCarrinhoIdeProdutoId(Long carrinhoId, Long produtoId) {
        itemCarrinhoRepository.deleteByCarrinhoIdAndProdutoId(carrinhoId, produtoId);
    }

    public Optional<ItemCarrinho> encontrarPorCarrinhoIdEProdutoId(Long carrinhoId, Long produtoId) {
        return itemCarrinhoRepository.findByCarrinhoIdAndProdutoId(carrinhoId, produtoId);
    }
}
