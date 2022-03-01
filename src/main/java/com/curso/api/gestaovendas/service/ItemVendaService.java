package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.repository.ItemVendaRepository;
import com.curso.api.gestaovendas.responseDTO.ItemVendaResponseDTO;
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

    public List<ItemVendaResponseDTO> getItemVendaByIdVenda(Long idVenda){
        Optional<List<ItemVenda>> optionalItemVendas = itemVendaRepository.findByVenda_Id(idVenda);
        if(optionalItemVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        List<ItemVendaResponseDTO> itemVendaResponseDTOS = new ArrayList<>();
        optionalItemVendas.get().forEach(itemVenda -> {
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
}
