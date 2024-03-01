package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.ItemCarrinho;
import com.luan.desafio.desafioestagio.model.ItemCarrinhoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinhoKey> {
    HashSet<ItemCarrinho> findAllByCarrinhoId(Long id);

    void deleteAllByCarrinhoId(Long id);
    @Modifying
    @Transactional
    void deleteByCarrinhoIdAndProdutoId(Long carrinhoId, Long produtoId);

    Optional<ItemCarrinho> findByCarrinhoIdAndProdutoId(Long carrinhoId, Long produtoId);
}