package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.VendaRepository;
import com.curso.api.gestaovendas.responseDTO.ClienteReponseGetVendasDTO;
import com.curso.api.gestaovendas.responseDTO.VendasGetAllResponseDTO;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    Convert convert = new Convert();

    @Autowired
    private VendaRepository vendaRepository;


    public Page<VendasGetAllResponseDTO> getAll(Pageable pageable){

        Page<Venda> vendas = vendaRepository.findAll(pageable);
        return new PageImpl<>(toPageVendasGetAllResponseDTO(vendas.getContent()));
    }

    public List<VendasGetAllResponseDTO> getVendasByCliente(String nome){
        Optional<List<Venda>> vendas = vendaRepository.findByCliente_NomeOrderByData(nome);
        if(vendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return toPageVendasGetAllResponseDTO(vendas.get());
    }

    private List<VendasGetAllResponseDTO> toPageVendasGetAllResponseDTO(List<Venda> vendas){
        List<VendasGetAllResponseDTO> vendasGetAllResponseDTOS = new ArrayList<>();
        vendas.forEach(venda -> {
            ClienteReponseGetVendasDTO clienteReponseGetVendasDTO = convert.toClienteReponseGetVendasDTO(venda.getCliente());
            VendasGetAllResponseDTO vendasGetAllResponseDTO = new VendasGetAllResponseDTO();
            vendasGetAllResponseDTO.setId(venda.getId());
            vendasGetAllResponseDTO.setData(venda.getData());
            vendasGetAllResponseDTO.setCliente(clienteReponseGetVendasDTO);
            vendasGetAllResponseDTOS.add(vendasGetAllResponseDTO);
        });
        return vendasGetAllResponseDTOS;
    }

    public List<VendasGetAllResponseDTO> getVendasByData(LocalDate data){
        if(data == null){
            throw new RegraNegocioException("Informe a data");
        }
        Optional<List<Venda>> optionalVendas = vendaRepository.findByData(data);
        if(optionalVendas.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return toPageVendasGetAllResponseDTO(optionalVendas.get());
    }
}
