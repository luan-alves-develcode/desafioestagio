package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Carrinho findCarrinhoByClienteId(Long id);
}
