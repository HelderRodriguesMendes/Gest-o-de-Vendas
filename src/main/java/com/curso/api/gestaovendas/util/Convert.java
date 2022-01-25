package com.curso.api.gestaovendas.util;

import com.curso.api.gestaovendas.dto.CategoriaResponseDTO;
import com.curso.api.gestaovendas.dto.ClienteResponseDTO;
import com.curso.api.gestaovendas.dto.ProdutoResponseDTO;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Convert {

    private ModelMapper modelMapper = new ModelMapper();

    public ClienteResponseDTO toClienteResponseDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria){
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto){
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }
}
