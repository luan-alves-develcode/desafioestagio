package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);
}