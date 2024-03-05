package com.luan.desafio.desafioestagio.repository;

import com.luan.desafio.desafioestagio.model.ItemVenda;
import com.luan.desafio.desafioestagio.model.ItemVendaKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, ItemVendaKey> {
}
