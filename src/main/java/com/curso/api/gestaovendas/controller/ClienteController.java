package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.requestDTO.ClienteRequestDTO;
import com.curso.api.gestaovendas.responseDTO.ClienteResponseDTO;
import com.curso.api.gestaovendas.service.ClienteService;
import com.curso.api.gestaovendas.util.Convert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    Convert convert = new Convert();

    @Autowired
    private ClienteService clienteService;
    List<Cliente> clientes = new ArrayList<>();

    @ApiOperation(value = "Listar todos os clientes", nickname = "listarClientes")
    @GetMapping("/getAll")
    public ResponseEntity<Page<ClienteResponseDTO>>getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        List<ClienteResponseDTO> clienteResponseDTOS = clienteService.getAll(pageable)
            .stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList());;
        return new ResponseEntity<>(new PageImpl<>(clienteResponseDTOS), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar cliente por ID", nickname = "bucarClienteId")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id){
        Cliente cliente = clienteService.getById(id);
        return new ResponseEntity<>(convert.toClienteResponseDTO(cliente), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar cliente por nome", nickname = "bucarClienteNome")
    @GetMapping("/getNome")
    public  ResponseEntity<List<ClienteResponseDTO>> getByNome(@RequestParam String nome){
        clientes = clienteService.getByNome(nome);
        return new ResponseEntity<>(clientes.stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList()), HttpStatus.OK);
    }
    @ApiOperation(value = "Bucar cliente por estado", nickname = "bucarClienteEstado")
    @GetMapping("/getEstado")
    public  ResponseEntity<List<ClienteResponseDTO>> getByEstado(@RequestParam String estado){
        clientes = clienteService.getByEstado(estado);
        return new ResponseEntity<>(clientes.stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList()), HttpStatus.OK);
    }
    @ApiOperation(value = "Bucar cliente por cidade", nickname = "bucarClienteCidade")
    @GetMapping("/getCidade")
    public  ResponseEntity<List<ClienteResponseDTO>> getByCidade(@RequestParam String cidade){
        clientes = clienteService.getByCidade(cidade);
        return new ResponseEntity<>(clientes.stream().map(cliente -> convert.toClienteResponseDTO(cliente)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "Salvar cliente", nickname = "salvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO>save(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteService.save(convert.toCliente(clienteRequestDTO));
        return new ResponseEntity<>(convert.toClienteResponseDTO(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Alterar cliente", nickname = "atualizarCliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        clienteRequestDTO.setId(id);
        Cliente cliente = clienteService.atualizar(convert.toCliente(clienteRequestDTO));
        return new ResponseEntity<>(convert.toClienteResponseDTO(cliente), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletar um cliente", nickname = "deletarCliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        clienteService.deletar(id);
    }
}
