package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.ItemCarrinhoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinhoKey> {
    List<ItemCarrinho> findAllByCarrinhoId(Long id);
}