package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.repository.ProdutoRepository;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    Convert convert = new Convert();

    public List<Produto> getAllProdutos(Pageable pageable){
        Page<Produto> produtoPage = produtoRepository.findAll(pageable);
        return produtoPage.getContent();
    }

    public List<Produto> getByNome(String nome){
        Optional<List<Produto>> listOptional = produtoRepository.getByNome(nome);
        if(listOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return listOptional.get();
    }

    public Produto getById(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return produtoOptional.get();
    }

    public Produto salvar(Produto produto){
        validateCategoryExist(produto.getCategoria());
        validaProdutoDuplicado(produto);
        Produto produtoSave = produtoRepository.save(produto);
        Categoria categoria = categoriaService.getById(produtoSave.getCategoria().getId());
        produtoSave.setCategoria(categoria);
        return produtoSave;
    }

    public Produto atualizar(Produto produto){
        if(getById(produto.getId()) == null){
            throw new EmptyResultDataAccessException(1);
        }
        validateCategoryExist(produto.getCategoria());
        return produtoRepository.save(produto);
    }

    public void deletar(Long id){
        if(getById(id) == null){
            throw new EmptyResultDataAccessException(1);
        }
        produtoRepository.deleteById(id);
    }

    private void validaProdutoDuplicado(Produto produto){
        Optional<Produto> produtoOptional = produtoRepository.findByCategoriaIdAndDescricao(produto.getCategoria().getId(), produto.getDescricao());
        if(produtoOptional.isPresent() && produto.getId() != produtoOptional.get().getId()){
            throw new RegraNegocioException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private void validateCategoryExist(Categoria categoria){
        if(categoria.getId() == null){
            throw new RegraNegocioException("Informe a categoria do produto");
        }
        categoriaService.getById(categoria.getId());
    }
}
