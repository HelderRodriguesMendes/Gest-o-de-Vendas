package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.responseDTO.ClienteResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.repository.ClienteRepository;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    Convert convert = new Convert();

    public List<ClienteResponseDTO> getAll(){
        return clienteRepository.findAll().stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList());
    }

    public ClienteResponseDTO getById(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return convert.toClienteResponseDTO(clienteOptional.get());
    }

    public List<ClienteResponseDTO> getByNome(String nome){
        return getListDTO(clienteRepository.getByNome(nome));
    }

    public List<ClienteResponseDTO> getByEstado(String estado){
        return getListDTO(clienteRepository.getByEstado(estado));
    }

    public List<ClienteResponseDTO> getByCidade(String cidade){
        return getListDTO(clienteRepository.getByCidade(cidade));
    }

    private List<ClienteResponseDTO> getListDTO(Optional<List<Cliente>> listOptional){
        if (listOptional.get().isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }else return listOptional.get().stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList());
    }

    public ClienteResponseDTO save(Cliente cliente){
        validarClienteDuplicado(cliente);
        return convert.toClienteResponseDTO(clienteRepository.save(cliente));
    }

    private void validarClienteDuplicado(Cliente cliente){
        Optional<Cliente> clienteBanco = clienteRepository.findByNome(cliente.getNome());
        if (clienteBanco.isPresent() && cliente.getId() != clienteBanco.get().getId()){
            throw new RegraNegocioException(String.format("O cliente %s já está cadastrado", cliente.getNome()));
        }
    }

}
