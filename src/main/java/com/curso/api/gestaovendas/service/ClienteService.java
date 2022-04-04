package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.exception.entidadesEnum.EntidadesMsgException;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll(Pageable pageable){
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return clientes.getContent();
    }

    public Cliente getById(Long id){
        if(id == null){
            throw  new RegraNegocioException("Informe o cliente");
        }
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CLIENTE.getEntidade()  + " " + id, 1);
        }
        return clienteOptional.get();
    }

    public List<Cliente> getByNome(String nome){
        return getListDTO(clienteRepository.getByNomeLike("%"+nome+"%"), nome);
    }

    public List<Cliente> getByEstado(String estado){
        return getListDTO(clienteRepository.getByEstado(estado), "");
    }

    public List<Cliente> getByCidade(String cidade){
        return getListDTO(clienteRepository.getByCidade(cidade), "");
    }

    private List<Cliente> getListDTO(Optional<List<Cliente>> listOptional, String filtro){
        if (listOptional.get().isEmpty()){
            if(filtro.equals("")){
                throw new EmptyResultDataAccessException(EntidadesMsgException.CLIENTE.getEntidade(), 1);
            }else{
                throw new EmptyResultDataAccessException(EntidadesMsgException.CLIENTE.getEntidade()  + " " + filtro, 1);
            }

        }else return listOptional.get();
    }

    public Cliente save(Cliente cliente){
        validarClienteDuplicado(cliente);
        cliente.setAtivo(true);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente){
        if(getById(cliente.getId()) == null){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CLIENTE.getEntidade(), 1);
        }
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id){
        if(getById(id) == null){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CLIENTE.getEntidade(), 1);
        }
        clienteRepository.deleteById(getById(id).getId());
    }

    private void validarClienteDuplicado(Cliente cliente){
        Optional<Cliente> clienteBanco = clienteRepository.getByNome(cliente.getNome());
        if (clienteBanco.isPresent() && cliente.getId() != clienteBanco.get().getId()){
            throw new RegraNegocioException(String.format("O cliente %s já está cadastrado", cliente.getNome()));
        }
    }

}
