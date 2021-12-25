package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
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

    @Autowired
    CategoriaService categoriaService;

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

    public Produto salvar(Produto produto){
        validateCategoryExist(produto.getCategoria());
        validaProdutoDuplicado(produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto){
        validateProdutoExist(produto.getId());
        validateCategoryExist(produto.getCategoria());
        return produtoRepository.save(produto);
    }

    private void validaProdutoDuplicado(Produto produto){
        if(produtoRepository.findByCategoriaIdAndDescricao(produto.getCategoria().getId(), produto.getDescricao()).isPresent()){
            throw new RegraNegocioException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private void validateCategoryExist(Categoria categoria){
        if(categoria.getId() == null){
            throw new RegraNegocioException("Informe a categoria do produto");
        }else if(categoriaService.getById(categoria.getId()).isEmpty()){
            throw new RegraNegocioException(String.format("A catetgoria %s informada não existe", categoria.getId()));
        }
    }

    private void validateProdutoExist(Long idProdudo){
        if(getIdProduto(idProdudo).isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
