package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.responseDTO.CategoriaResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.repository.CategoriaRepository;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    Convert convert = new Convert();

    public CategoriaResponseDTO salvar(Categoria categoria){
        categoriaIsPresentName(categoria);
        return convert.toCategoriaResponseDTO(categoriaRepository.save(categoria));
    }

    public Page<CategoriaResponseDTO> getAll(Pageable pageable){
        List<CategoriaResponseDTO> categoriaResponseDTOS = categoriaRepository.findAll(pageable).stream().map(categoria -> convert.toCategoriaResponseDTO(categoria)).collect(Collectors.toList());
        return new PageImpl<>(categoriaResponseDTOS);
    }

    public CategoriaResponseDTO getById(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return convert.toCategoriaResponseDTO(categoriaOptional.get());
    }

    public List<CategoriaResponseDTO> findByNome(String nome){
        Optional<List<Categoria>> optionalCategorias = categoriaRepository.getByNome(nome);
        if(optionalCategorias.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else{
            return optionalCategorias.stream().map(categoria -> convert.toCategoriaResponseDTO((Categoria) categoria)).collect(Collectors.toList());
        }
    }

    public CategoriaResponseDTO atualizar(Categoria categoria){
        if(getById(categoria.getId())== null){
            throw new EmptyResultDataAccessException(1);
        } else{
            categoriaIsPresentName(categoria);
            Categoria categoriaSalva = categoriaRepository.save(categoria);
            return convert.toCategoriaResponseDTO(categoriaSalva);
        }
    }

    public void deletar(Long id){
        categoriaRepository.deleteById(getById(id).getId());
    }

    private void categoriaIsPresentName(Categoria categoria){
        Categoria categoriaIsPresent = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaIsPresent != null && categoriaIsPresent.getId() != categoria.getId()){
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada", categoria.getNome()));
        }
    }
}
