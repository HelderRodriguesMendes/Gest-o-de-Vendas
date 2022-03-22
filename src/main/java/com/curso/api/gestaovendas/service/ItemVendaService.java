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
        this.validarItemVenda(itemVendaRequestDTOS);
        List<ItemVenda> itemVendasSalvas = new ArrayList<>();
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {

            Produto produto = this.validateProdutoExist(itemVendaRequestDTO.getIdProduto());
            produto.setQuantidade(produto.getQuantidade() - itemVendaRequestDTO.getQuantidade());

            ItemVenda itemVenda = new ItemVenda(itemVendaRequestDTO.getQuantidade(), itemVendaRequestDTO.getPrecoVendido(), produto, vendaSalva);
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

    private void validarItemVenda(List<ItemVendaRequestDTO> itemVendaRequestDTOS){
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {
            Produto produto = this.validateProdutoExist(itemVendaRequestDTO.getIdProduto());
            if(produto.getQuantidade() < itemVendaRequestDTO.getQuantidade()){
                throw new RegraNegocioException("Há apenas " + produto.getQuantidade() + " unidades disponiveis do produto " + produto.getDescricao());
            }
        });
    }

    private Produto validateProdutoExist(Long idProduto){
        if(idProduto == null){
            throw new RegraNegocioException("Informe o produto");
        }
        return produtoService.getById(idProduto);
    }
}
