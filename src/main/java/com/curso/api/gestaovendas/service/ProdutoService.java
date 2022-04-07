package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.exception.entidadesEnum.EntidadesMsgException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.repository.ProdutoRepository;
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
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Produto> getAllProdutos(Pageable pageable){
        Page<Produto> produtoPage = produtoRepository.findAll(pageable);
        return produtoPage.getContent();
    }

    public List<Produto> getByNome(String nome){
        Optional<List<Produto>> listOptional = produtoRepository.getByNome(nome);
        if(listOptional.get().size() <= 0){
            throw new EmptyResultDataAccessException(EntidadesMsgException.PRODUTO.getEntidade(), 1);
        }else return listOptional.get();
    }

    public Produto getById(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.PRODUTO.getEntidade(), 1);
        }
        return produtoOptional.get();
    }

    public List<Produto> getByCategoria(Long idCategoria){
        return produtoRepository.findByCategoria(idCategoria);
    }

    public Produto salvar(Produto produto){
        validateCategoryExist(produto.getCategoria());
        validaProdutoDuplicado(produto);
        Categoria categoria = categoriaService.getById(produto.getCategoria().getId());
        produto.setCategoria(categoria);
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto){
        if(getById(produto.getId()) == null){
            throw new EmptyResultDataAccessException(EntidadesMsgException.PRODUTO.getEntidade(), 1);
        }
        validateCategoryExist(produto.getCategoria());
        return produtoRepository.save(produto);
    }

    public void deletar(Long id){
        produtoRepository.desativar(id);
    }

    public void deletarPorCategoria(Long idCategoria){
        List<Produto> produtos = getByCategoria(idCategoria);
        produtos.forEach(produto -> deletar(produto.getId()));
    }

    private void validaProdutoDuplicado(Produto produto){
        Optional<Produto> produtoOptional = produtoRepository.getByCategoriaIdAndDescricao(produto.getCategoria().getId(), produto.getDescricao());
        if(produtoOptional.isPresent() && produto.getId() != produtoOptional.get().getId()){
            throw new RegraNegocioException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private Categoria validateCategoryExist(Categoria categoria){
        if(categoria.getId() == null){
            throw new RegraNegocioException("Informe a categoria do produto");
        }
        return categoriaService.getById(categoria.getId());
    }
}
