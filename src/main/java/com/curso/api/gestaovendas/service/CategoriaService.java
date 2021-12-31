package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.dto.CategoriaResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO salvar(Categoria categoria){
        categoriaIsPresentName(categoria);
        return CategoriaResponseDTO.toDTO(categoriaRepository.save(categoria));
    }

    public List<CategoriaResponseDTO> getAllCategoria(){
        return categoriaRepository.findAll().stream().map(categoria -> CategoriaResponseDTO.toDTO(categoria)).collect(Collectors.toList());
    }

    public CategoriaResponseDTO getById(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return CategoriaResponseDTO.toDTO(categoriaOptional.get());
    }

    public List<CategoriaResponseDTO> findByNome(String nome){
        Optional<List<Categoria>> optionalCategorias = categoriaRepository.getByNome(nome);
        if(optionalCategorias.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else{
            return optionalCategorias.stream().map(categoria -> CategoriaResponseDTO.toDTO((Categoria) categoria)).collect(Collectors.toList());
        }
    }

    public CategoriaResponseDTO atualizar(Categoria categoria){
        if(getById(categoria.getId())== null){
            throw new EmptyResultDataAccessException(1);
        } else{
            categoriaIsPresentName(categoria);
            Categoria categoriaSalva = categoriaRepository.save(categoria);
            return CategoriaResponseDTO.toDTO(categoriaSalva);
        }
    }

    public void deletar(Long id){
        categoriaRepository.deleteById(id);
    }

    private void categoriaIsPresentName(Categoria categoria){
        Categoria categoriaIsPresent = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaIsPresent != null && categoriaIsPresent.getId() != categoria.getId()){
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada", categoria.getNome()));
        }
    }
}
