package com.curso.api.gestaovendas.util;

import com.curso.api.gestaovendas.model.*;
import com.curso.api.gestaovendas.requestDTO.CategoriaRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ClienteRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ItemVendaRequestDTO;
import com.curso.api.gestaovendas.requestDTO.ProdutoRequestDTO;
import com.curso.api.gestaovendas.responseDTO.*;
import com.curso.api.gestaovendas.service.ItemVendaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Convert {

    private ModelMapper modelMapper = new ModelMapper();

    public ClienteResponseDTO toClienteResponseDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDTO.class);
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

    //###########################################################################

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO) {
        return modelMapper.map(produtoRequestDTO, Produto.class);
    }

    //###########################################################################

    public List<ItemVendaResponseDTO> toListItemVendaResponseDTO(List<ItemVenda> vendas) {
        List<ItemVendaResponseDTO> itemVendaResponseDTOS = new ArrayList<>();
        vendas.forEach(itemVenda -> {
            ItemVendaResponseDTO itemVendaResponseDTO = new ItemVendaResponseDTO();
            itemVendaResponseDTO.setIdItemVenda(itemVenda.getId());
            itemVendaResponseDTO.setQuantidade(itemVenda.getQuantidade());
            itemVendaResponseDTO.setPrecoVendido(itemVenda.getPrecoVendido());
            itemVendaResponseDTO.setIdProduto(itemVenda.getProduto().getId());
            itemVendaResponseDTO.setDescricaoProduto(itemVenda.getProduto().getDescricao());
            itemVendaResponseDTOS.add(itemVendaResponseDTO);
        });
        return itemVendaResponseDTOS;
    }


    public List<VendaResponseDTO> toListVendaResponseDTO(List<Venda> vendas, ItemVendaService itemVendaService) {
        List<VendaResponseDTO> vendaResponseDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            vendaResponseDTOS.add(toVendaResponseDTO(venda, itemVendaService));
        });
        return vendaResponseDTOS;
    }

    public VendaResponseDTO toVendaResponseDTO(Venda vendaSalva,
                                               List<ItemVendaRequestDTO> itemVendaRequestDTOS,
                                               ItemVendaService itemVendaService){

        List<ItemVenda> itemVendas = itemVendaService.salvar(vendaSalva, itemVendaRequestDTOS);

        VendaResponseDTO vendaResponseDTO = new VendaResponseDTO();
        vendaResponseDTO.setId(vendaSalva .getId());
        vendaResponseDTO.setData(vendaSalva.getData());
        vendaResponseDTO.setItemVendaDTOS(toListItemVendaResponseDTO(itemVendas));
        return vendaResponseDTO;
    }

    public VendaResponseDTO toVendaResponseDTO(Venda venda, ItemVendaService itemVendaService){
        List<ItemVendaResponseDTO> itemVendaDTOS = toListItemVendaResponseDTO(itemVendaService.getItemVendaByIdVenda(venda.getId()));
        return new VendaResponseDTO(venda.getId(), venda.getData(), itemVendaDTOS);
    }

    public VendaResponseDTO toVendaResponseDTO(List<ItemVenda> itemVendas, Venda venda){
        return new VendaResponseDTO(venda.getId(), venda.getData(), toListItemVendaResponseDTO(itemVendas));
    }
}
