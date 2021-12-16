package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
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
        categoriaIsPresentName(categoria);
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategoria(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getById(Long id){
         return categoriaRepository.findById(id);
    }

    public List<Categoria> findByNome(String nome){
        Optional<List<Categoria>> optionalCategorias = categoriaRepository.getByNome(nome);
        if(optionalCategorias.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return optionalCategorias.get();
    }

    public Categoria atualizar(Categoria categoria){
        if(getById(categoria.getId()).isEmpty()){
            throw new EmptyResultDataAccessException(1);
        } else{
            categoriaIsPresentName(categoria);
            return categoriaRepository.save(categoria);
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
