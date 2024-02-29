package com.luan.desafio.desafioestagio.service;

import com.luan.desafio.desafioestagio.dto.CadastrarProdutoDto;
import com.luan.desafio.desafioestagio.model.Produto;
import com.luan.desafio.desafioestagio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findProdutoById(Long id) {
        return produtoRepository.getReferenceById(id);
    }

    public Produto salvar(CadastrarProdutoDto dto) {
        Produto produto = new Produto(dto);
        return produtoRepository.save(produto);
    }
}
