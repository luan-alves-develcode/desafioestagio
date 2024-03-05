package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpfOrEmail(String cpf, String email);
}
