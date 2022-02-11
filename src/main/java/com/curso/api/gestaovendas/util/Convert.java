package com.curso.api.gestaovendas.util;

import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.requestDTO.CategoriaRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ClienteRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ProdutoRequestDTO;
import com.curso.api.gestaovendas.responseDTO.*;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Produto;
import org.modelmapper.ModelMapper;

public class Convert {

    private ModelMapper modelMapper = new ModelMapper();

    public ClienteResponseDTO toClienteResponseDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public ClienteReponseGetVendasDTO toClienteReponseGetVendasDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteReponseGetVendasDTO.class);
    }

    public Cliente toCliente(ClienteRequestDTO clienteRequestDTO){
        return modelMapper.map(clienteRequestDTO, Cliente.class);
    }

    //###########################################################################

    public CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria){
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public Categoria toCategoria(CategoriaRequestDTO categoriaRequestDTO){
        return modelMapper.map(categoriaRequestDTO, Categoria.class);
    }
    public Categoria toCategoria(CategoriaResponseDTO categoriaResponseDTO){
        return modelMapper.map(categoriaResponseDTO, Categoria.class);
    }

    //###########################################################################

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto){
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO){
        return modelMapper.map(produtoRequestDTO, Produto.class);
    }

    public VendasGetAllResponseDTO toVendasGetAllResponseDTO(Venda venda){
        return modelMapper.map(venda, VendasGetAllResponseDTO.class);
    }
}
