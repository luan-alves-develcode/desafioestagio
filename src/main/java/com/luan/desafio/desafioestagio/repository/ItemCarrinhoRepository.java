package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.ItemCarrinhoKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinhoKey> {
    List<ItemCarrinho> findAllByCarrinhoId(Long id);

    void deleteAllByCarrinhoId(Long id);
}