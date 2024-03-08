package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.CadastrarProdutoDto;
import com.luan.desafio.desafioestagio.exception.ValidacaoException;
import com.luan.desafio.desafioestagio.model.Produto;
import com.luan.desafio.desafioestagio.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findProdutoById(Long id) {
        var optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public Produto salvar(CadastrarProdutoDto dto) {
        boolean jaCadastrado = produtoRepository.existsByNome(dto.getNome());
        if (jaCadastrado) {
            throw new ValidacaoException("Produto com este nome já existe.");
        }
        return produtoRepository.save(new Produto(dto));
    }
}
