package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> getByNome(String nome){
        Optional<List<Produto>> listOptional = produtoRepository.getByNome(nome);
        if(listOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return listOptional.get();
    }

    public Optional<Produto> getIdProduto(Long id){
        return produtoRepository.findById(id);
    }
}
