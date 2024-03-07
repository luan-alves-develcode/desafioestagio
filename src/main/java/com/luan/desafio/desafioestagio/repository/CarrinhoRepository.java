package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findCarrinhoByClienteId(Long id);
}
