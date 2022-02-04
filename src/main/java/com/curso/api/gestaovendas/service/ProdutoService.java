package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.responseDTO.ProdutoResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.repository.ProdutoRepository;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    Convert convert = new Convert();

    public List<ProdutoResponseDTO> getAllProdutos(){
        return produtoRepository.findAll().stream().map(produto -> convert.toProdutoResponseDTO(produto)).collect(Collectors.toList());
    }

    public List<ProdutoResponseDTO> getByNome(String nome){
        Optional<List<Produto>> listOptional = produtoRepository.getByNome(nome);
        if(listOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return listOptional.get().stream().map(produto -> convert.toProdutoResponseDTO(produto)).collect(Collectors.toList());
    }

    public ProdutoResponseDTO getIdProduto(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return convert.toProdutoResponseDTO(produtoOptional.get());
    }

    public ProdutoResponseDTO salvar(Produto produto){
        validateCategoryExist(produto.getCategoria());
        validaProdutoDuplicado(produto);
        return convert.toProdutoResponseDTO(produtoRepository.save(produto));
    }

    public ProdutoResponseDTO atualizar(Produto produto){
        validateProdutoExist(produto.getId());
        validateCategoryExist(produto.getCategoria());
        return convert.toProdutoResponseDTO(produtoRepository.save(produto));
    }

    public void deletar(Long idProduto){
        validateProdutoExist(idProduto);
        produtoRepository.deleteById(idProduto);
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
        }else if(categoriaService.getById(categoria.getId()) == null){
            throw new RegraNegocioException(String.format("A catetgoria %s informada não existe", categoria.getId()));
        }
    }

    private void validateProdutoExist(Long idProdudo){
        if(getIdProduto(idProdudo) == null){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
