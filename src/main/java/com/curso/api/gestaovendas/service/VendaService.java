package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.VendaRepository;
import com.curso.api.gestaovendas.requestDTO.VendaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private ClienteService clienteService;

    public Venda salvarVendaRequestDTO(Long idCliente, VendaRequestDTO vendaRequestDTO){
        itemVendaService.validarItemVenda(vendaRequestDTO.getItemVendaRequestDTOS());
        Cliente cliente = clienteService.getById(idCliente);
         return vendaRepository.save(new Venda(vendaRequestDTO.getData(), cliente));
    }

    public List<Venda> findVendaByIdCliente(Long idCliente){
        clienteService.getById(idCliente);
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_Id(idCliente);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }

    public List<Venda> getVendaByData(String data){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByData(LocalDate.parse(data));
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }

    public Venda getVendaById(Long id){
        Optional<Venda> optionalVendas = vendaRepository.findById(id);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }

}
