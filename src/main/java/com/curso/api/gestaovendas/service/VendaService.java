package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.VendaRepository;
import com.curso.api.gestaovendas.responseDTO.ClienteReponseGetVendasDTO;
import com.curso.api.gestaovendas.responseDTO.VendasGetAllResponseDTO;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    Convert convert = new Convert();

    @Autowired
    private VendaRepository vendaRepository;


    public Page<VendasGetAllResponseDTO> getAll(Pageable pageable){
        List<VendasGetAllResponseDTO> vendasGetAllResponseDTOS = new ArrayList<>();
        Page<Venda> vendas = vendaRepository.findAll(pageable);
        vendas.forEach(venda -> {
            ClienteReponseGetVendasDTO clienteReponseGetVendasDTO = convert.toClienteReponseGetVendasDTO(venda.getCliente());
            VendasGetAllResponseDTO vendasGetAllResponseDTO = new VendasGetAllResponseDTO();
            vendasGetAllResponseDTO.setId(venda.getId());
            vendasGetAllResponseDTO.setData(venda.getData());
            vendasGetAllResponseDTO.setCliente(clienteReponseGetVendasDTO);
            vendasGetAllResponseDTOS.add(vendasGetAllResponseDTO);
        });
        return new PageImpl<>(vendasGetAllResponseDTOS);
    }
}
