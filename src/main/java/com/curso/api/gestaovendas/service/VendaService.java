package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.VendaRepository;
import com.curso.api.gestaovendas.responseDTO.VendaRespondeDTO;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    Convert convert = new Convert();

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaService itemVendaService;

    public List<VendaRespondeDTO> getVendaByIdCliente(Long idCliente){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_IdOrderByData(idCliente);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return this.getListVendaRespondeDTO(optionalVendas.get());
    }

    public List<VendaRespondeDTO> getVendaByNomeCliente(String nome){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_NomeOrderByData(nome);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return this.getListVendaRespondeDTO(optionalVendas.get());
    }

    private List<VendaRespondeDTO> getListVendaRespondeDTO(List<Venda> vendas){
        List<VendaRespondeDTO> vendaRespondeDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            VendaRespondeDTO vendaRespondeDTO = new VendaRespondeDTO();
            vendaRespondeDTO.setId(venda.getId());
            vendaRespondeDTO.setData(venda.getData());
            vendaRespondeDTO.setItemVendaDTOS(itemVendaService.getItemVendaByIdVenda(venda.getId()));
            vendaRespondeDTOS.add(vendaRespondeDTO);
        });
        return vendaRespondeDTOS;
    }
}
