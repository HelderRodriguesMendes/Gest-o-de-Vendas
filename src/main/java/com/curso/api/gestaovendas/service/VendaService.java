package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.VendaRepository;
import com.curso.api.gestaovendas.requestDTO.VendaRequestDTO;
import com.curso.api.gestaovendas.responseDTO.VendaRespondeDTO;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    Convert convert = new Convert();

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public Venda salvarVenda(Venda venda){
        return vendaRepository.save(venda);
    }

    public VendaRespondeDTO salvarVendaRequestDTO(Long idCliente, VendaRequestDTO vendaRequestDTO){
        Cliente cliente = clienteService.getById(idCliente);
        itemVendaService.validarItemVenda(vendaRequestDTO.getItemVendaRequestDTOS());

        Venda venda = new Venda();
        venda.setData(vendaRequestDTO.getData());
        venda.setCliente(cliente);
        Venda vendaSalva = salvarVenda(venda);
        List<ItemVenda> itemVendas =itemVendaService.salvar(vendaSalva, vendaRequestDTO.getItemVendaRequestDTOS());

        VendaRespondeDTO vendaRespondeDTO = new VendaRespondeDTO();
        vendaRespondeDTO.setId(vendaSalva .getId());
        vendaRespondeDTO.setData(vendaSalva.getData());
        vendaRespondeDTO.setItemVendaDTOS(convert.toListItemVendaResponseDTO(itemVendas));
        return vendaRespondeDTO;
    }

    public List<Venda> getVendaByIdCliente(Long idCliente){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_IdOrderByData(idCliente);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }

    public List<Venda> getVendaByNomeCliente(String nome){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByCliente_NomeOrderByData(nome);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }

    public List<Venda> getVendaByData(String data){
        Optional<List<Venda>> optionalVendas = vendaRepository.findByDataOrderByData(data);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalVendas.get();
    }
}
