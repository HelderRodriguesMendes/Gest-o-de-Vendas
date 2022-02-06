package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.requestDTO.ClienteRequestDTO;
import com.curso.api.gestaovendas.responseDTO.ClienteResponseDTO;
import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.service.ClienteService;
import com.curso.api.gestaovendas.util.Convert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    Convert convert = new Convert();

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Listar todos os clientes", nickname = "listarClientes")
    @GetMapping("/getAll")
    public ResponseEntity<Page<ClienteResponseDTO>>getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<>(clienteService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar cliente por ID", nickname = "bucarClienteId")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<ClienteResponseDTO>(clienteService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar cliente por nome", nickname = "bucarClienteNome")
    @GetMapping("/getNome")
    public  ResponseEntity<List<ClienteResponseDTO>> getByNome(@RequestParam String nome){
        return new ResponseEntity<>(clienteService.getByNome(nome), HttpStatus.OK);
    }
    @ApiOperation(value = "Bucar cliente por estado", nickname = "bucarClienteEstado")
    @GetMapping("/getEstado")
    public  ResponseEntity<List<ClienteResponseDTO>> getByEstado(@RequestParam String estado){
        return new ResponseEntity<>(clienteService.getByEstado(estado), HttpStatus.OK);
    }
    @ApiOperation(value = "Bucar cliente por cidade", nickname = "bucarClienteCidade")
    @GetMapping("/getCidade")
    public  ResponseEntity<List<ClienteResponseDTO>> getByCidade(@RequestParam String cidade){
        return new ResponseEntity<>(clienteService.getByCidade(cidade), HttpStatus.OK);
    }

    @ApiOperation(value = "Salvar cliente", nickname = "salvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO>save(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        return new ResponseEntity<>(clienteService.save(convert.toCliente(clienteRequestDTO)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Alterar cliente", nickname = "salvarCliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        clienteRequestDTO.setId(id);
        return new ResponseEntity<>(clienteService.atualizar(convert.toCliente(clienteRequestDTO)), HttpStatus.CREATED);
    }
}
