package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategoria(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getById(Long id){
         return categoriaRepository.findById(id);
    }

    public Categoria atualizar(Categoria categoria){
        if(!categoriaIsPresent(categoria.getId())){
            throw new EmptyResultDataAccessException(1);
        } else return categoriaRepository.save(categoria);
    }

    public boolean categoriaIsPresent(Long id){
        return categoriaRepository.findById(id).isPresent();
    }

    public void deletar(Long id){
        categoriaRepository.deleteById(id);
    }
}
