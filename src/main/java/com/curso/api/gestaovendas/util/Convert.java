package com.curso.api.gestaovendas.util;

import com.curso.api.gestaovendas.model.*;
import com.curso.api.gestaovendas.requestDTO.CategoriaRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ClienteRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ProdutoRequestDTO;
import com.curso.api.gestaovendas.responseDTO.*;
import com.curso.api.gestaovendas.service.ItemVendaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ItemVendaService itemVendaService;

    public ClienteResponseDTO toClienteResponseDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public ClienteReponseGetVendasDTO toClienteReponseGetVendasDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteReponseGetVendasDTO.class);
    }

    public Cliente toCliente(ClienteRequestDTO clienteRequestDTO) {
        return modelMapper.map(clienteRequestDTO, Cliente.class);
    }

    //###########################################################################

    public CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public Categoria toCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        return modelMapper.map(categoriaRequestDTO, Categoria.class);
    }

    public Categoria toCategoria(CategoriaResponseDTO categoriaResponseDTO) {
        return modelMapper.map(categoriaResponseDTO, Categoria.class);
    }

    //###########################################################################

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO) {
        return modelMapper.map(produtoRequestDTO, Produto.class);
    }

    public List<ItemVendaResponseDTO> toListItemVendaResponseDTO(List<ItemVenda> vendas) {
        List<ItemVendaResponseDTO> itemVendaResponseDTOS = new ArrayList<>();
        vendas.forEach(itemVenda -> {
            ItemVendaResponseDTO itemVendaResponseDTO = new ItemVendaResponseDTO();
            itemVendaResponseDTO.setId(itemVenda.getId());
            itemVendaResponseDTO.setQuantidade(itemVenda.getQuantidade());
            itemVendaResponseDTO.setPrecoVendido(itemVenda.getPrecoVendido());
            itemVendaResponseDTO.setIdProduto(itemVenda.getProduto().getId());
            itemVendaResponseDTO.setDescricaoProduto(itemVenda.getProduto().getDescricao());
            itemVendaResponseDTOS.add(itemVendaResponseDTO);
        });
        return itemVendaResponseDTOS;
    }

    public List<ClienteVendaResponseDTO> toListClienteVendaResponseDTO(List<Venda> vendas) {
        List<ItemVendaResponseDTO> itemVendaResponseDTOS =toListItemVendaResponseDTO(itemVendaService.getItemVendaByIdVenda(vendas.stream().count()));
        List<ClienteVendaResponseDTO> clienteVendaResponseDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            ClienteVendaResponseDTO clienteVendaResponseDTO = new ClienteVendaResponseDTO();
            clienteVendaResponseDTO.setId(venda.getCliente().getId());
            clienteVendaResponseDTO.setNomeCliente(venda.getCliente().getNome());
            clienteVendaResponseDTO.setVendas(this.toVendaRespondeDTO(vendas, itemVendaResponseDTOS));
            clienteVendaResponseDTOS.add(clienteVendaResponseDTO);
        });
        return clienteVendaResponseDTOS;
    }

    public List<VendaRespondeDTO> toVendaRespondeDTO(List<Venda> vendas, List<ItemVendaResponseDTO> itemVendaResponseDTOS) {
        List<VendaRespondeDTO> vendaRespondeDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            VendaRespondeDTO vendaRespondeDTO = new VendaRespondeDTO();
            vendaRespondeDTO.setId(venda.getId());
            vendaRespondeDTO.setData(venda.getData());
            vendaRespondeDTO.setItemVendaDTOS(itemVendaResponseDTOS);
            vendaRespondeDTOS.add(vendaRespondeDTO);
        });
        return vendaRespondeDTOS;
    }

    public List<ClienteVendaResponseDTO> getListClienteVendaResponseDTO(List<Venda> vendas){
        List<ClienteVendaResponseDTO> clienteVendaResponseDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            ClienteVendaResponseDTO clienteVendaResponseDTO = new ClienteVendaResponseDTO();
            clienteVendaResponseDTO.setId(venda.getCliente().getId());
            clienteVendaResponseDTO.setNomeCliente(venda.getCliente().getNome());
            clienteVendaResponseDTO.setVendas(this.getVendaRespondeDTO(vendas));
        });
        return clienteVendaResponseDTOS;
    }

    public List<VendaRespondeDTO> getVendaRespondeDTO(List<Venda> vendas) {
        List<VendaRespondeDTO> vendaRespondeDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            VendaRespondeDTO vendaRespondeDTO = new VendaRespondeDTO();
            vendaRespondeDTO.setId(venda.getId());
            vendaRespondeDTO.setData(venda.getData());
            vendaRespondeDTO.setItemVendaDTOS(toListItemVendaResponseDTO(itemVendaService.getItemVendaByIdVenda(venda.getId())));
            vendaRespondeDTOS.add(vendaRespondeDTO);
        });
        return vendaRespondeDTOS;
    }
}
