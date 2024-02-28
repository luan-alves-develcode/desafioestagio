package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.CadastrarClienteDto;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Cliente salvar(CadastrarClienteDto dto) {
        Cliente cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }
    public Cliente findById(Long id) {
        return clienteRepository.getReferenceById(id);
    }
}
