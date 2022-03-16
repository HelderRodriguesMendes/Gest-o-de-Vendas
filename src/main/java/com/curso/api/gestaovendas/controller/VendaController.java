package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.requestDTO.VendaRequestDTO;
import com.curso.api.gestaovendas.responseDTO.ClienteVendaResponseDTO;
import com.curso.api.gestaovendas.responseDTO.VendaRespondeDTO;
import com.curso.api.gestaovendas.service.VendaService;
import com.curso.api.gestaovendas.util.Convert;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    Convert convert = new Convert();
    List<Venda> vendas = new ArrayList<>();

    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<VendaRespondeDTO> salvar( @PathVariable Long idCliente, @RequestBody VendaRequestDTO vendaRequestDTO){
        return new ResponseEntity<>(vendaService.salvarVendaRequestDTO(idCliente, vendaRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ClienteVendaResponseDTO>> getVendaByIdCliente(@PathVariable Long id){
        vendas = vendaService.getVendaByIdCliente(id);
        return new ResponseEntity<>(convert.toListClienteVendaResponseDTO(vendas), HttpStatus.OK);
    }

    @GetMapping("/getVendaByNomeCliente")
    public ResponseEntity<List<ClienteVendaResponseDTO>> getVendaByNomeCliente(@RequestParam String nome){
        vendas = vendaService.getVendaByNomeCliente(nome);
        return new ResponseEntity<>( convert.getListClienteVendaResponseDTO(vendas), HttpStatus.OK);
    }

    @GetMapping("/getVendaByData")
    public ResponseEntity<List<VendaRespondeDTO>> getVendaByData(@RequestParam String data){
        vendas = vendaService.getVendaByData(data);
        return new ResponseEntity<>(convert.getVendaRespondeDTO(vendas), HttpStatus.OK);
    }
}