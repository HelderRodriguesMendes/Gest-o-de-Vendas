package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.entidadesEnum.EntidadesMsgException;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.ItemVenda;
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

    public Venda salvar(Long idCliente, VendaRequestDTO vendaRequestDTO){
        itemVendaService.validarItemVenda(vendaRequestDTO.getItemVendaRequestDTOS());
        Cliente cliente = clienteService.getById(idCliente);
         return vendaRepository.save(new Venda(vendaRequestDTO.getData(), cliente));
    }

    public List<Venda> getAllVendas(){
        return vendaRepository.findAll();
    }

    public List<Venda> findVendaByIdCliente(Long idCliente){
        clienteService.getById(idCliente);
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_Id(idCliente);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.VENDA.getEntidade(), 1);
        }
        return optionalVendas.get();
    }

    public List<Venda> getVendaByData(String data){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByData(LocalDate.parse(data));
        if( optionalVendas.get().size() <= 0){
            throw new EmptyResultDataAccessException(EntidadesMsgException.VENDA.getEntidade(), 1);
        }
        return optionalVendas.get();
    }

    public Venda getVendaById(Long id){
        Optional<Venda> optionalVendas = vendaRepository.findById(id);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.VENDA.getEntidade(), 1);
        }
        return optionalVendas.get();
    }

    public Venda atualizar(Long idVenda, Long idCliente, VendaRequestDTO vendaRequestDTO){
        getVendaById(idVenda);
        Cliente cliente = clienteService.getById(idCliente);
        return vendaRepository.save(new Venda(idVenda, vendaRequestDTO.getData(), cliente));
    }

    public void deletar(Long idVenda){
        getVendaById(idVenda);
        List<ItemVenda> itemVendas = itemVendaService.getItemVendaByIdVenda(idVenda);
        itemVendas.stream().forEach(itemVenda -> itemVendaService.deletar(itemVenda.getId()));
        vendaRepository.deleteById(idVenda);
    }

}
