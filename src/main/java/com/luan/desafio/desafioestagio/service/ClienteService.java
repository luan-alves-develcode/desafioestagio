package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.CadastrarClienteDto;
import com.luan.desafio.desafioestagio.exception.ValidacaoException;
import com.luan.desafio.desafioestagio.model.Cliente;
import com.luan.desafio.desafioestagio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Cliente salvar(CadastrarClienteDto dto) {
        boolean jaCadastrado = clienteRepository.existsByCpfOrEmail(dto.getCpf(), dto.getEmail());
        if (jaCadastrado) {
            throw new ValidacaoException("Email ou senha já cadastrados.");
        }
        return clienteRepository.save(new Cliente(dto));
    }
    public Cliente encontrarPorId(Long id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        return optional.orElseThrow(() -> new ValidacaoException("Cliente não encontrado"));
    }
}
