package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.ItemVendaRepository;
import com.curso.api.gestaovendas.requestDTO.ItemVendaRequestDTO;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoService produtoService;

    Convert convert = new Convert();

    public List<ItemVenda> salvar(Venda vendaSalva, List<ItemVendaRequestDTO> itemVendaRequestDTOS){
        List<ItemVenda> itemVendasSalvas = new ArrayList<>();
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {
            Produto produto = produtoService.getById(itemVendaRequestDTO.getIdProduto());
            produto.setQuantidade(produto.getQuantidade() - itemVendaRequestDTO.getQuantidade());
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setQuantidade(itemVendaRequestDTO.getQuantidade());
            itemVenda.setPrecoVendido(itemVendaRequestDTO.getPrecoVendido());
            itemVenda.setProduto(produto);
            itemVenda.setVenda(vendaSalva);
            itemVendaRepository.save(itemVenda);
            itemVendasSalvas.add(itemVendaRepository.save(itemVenda));
        });
        return itemVendasSalvas;
    }

    public List<ItemVenda> getItemVendaByIdVenda(Long idVenda){
        Optional<List<ItemVenda>> optionalItemVendas = itemVendaRepository.findByVenda_Id(idVenda);
        if(optionalItemVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalItemVendas.get();
    }

    public void validarItemVenda(List<ItemVendaRequestDTO> itemVendaRequestDTOS){
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {
            Produto produto = produtoService.getById(itemVendaRequestDTO.getIdProduto());
            if(produto.getQuantidade() < itemVendaRequestDTO.getQuantidade()){
                throw new RegraNegocioException("Há apenas " + produto.getQuantidade() + " unidades disponiveis do produto " + produto.getDescricao());
            }
        });
    }
}