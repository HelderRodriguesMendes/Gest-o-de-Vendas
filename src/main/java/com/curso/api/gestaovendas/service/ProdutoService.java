package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.dto.ProdutoResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public List<ProdutoResponseDTO> getAllProdutos(){
        return produtoRepository.findAll().stream().map(produto -> ProdutoResponseDTO.toDTO(produto)).collect(Collectors.toList());
    }

    public List<ProdutoResponseDTO> getByNome(String nome){
        Optional<List<Produto>> listOptional = produtoRepository.getByNome(nome);
        if(listOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return listOptional.get().stream().map(produto -> ProdutoResponseDTO.toDTO(produto)).collect(Collectors.toList());
    }

    public Optional<ProdutoResponseDTO> getIdProduto(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return Optional.of(ProdutoResponseDTO.toDTO(produtoOptional.get()));
    }

    public ProdutoResponseDTO salvar(Produto produto){
        validateCategoryExist(produto.getCategoria());
        validaProdutoDuplicado(produto);
        return ProdutoResponseDTO.toDTO(produtoRepository.save(produto));
    }

    public ProdutoResponseDTO atualizar(Produto produto){
        validateProdutoExist(produto.getId());
        validateCategoryExist(produto.getCategoria());
        return ProdutoResponseDTO.toDTO(produtoRepository.save(produto));
    }

    public void deletar(Long idProduto){
        validateProdutoExist(idProduto);
        produtoRepository.deleteById(idProduto);
    }

    private void validaProdutoDuplicado(Produto produto){
        if(produtoRepository.findByCategoriaIdAndDescricao(produto.getCategoria().getId(), produto.getDescricao()).isPresent()){
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
        if(getIdProduto(idProdudo).isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
