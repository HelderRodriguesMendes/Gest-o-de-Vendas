package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.responseDTO.VendaRespondeDTO;
import com.curso.api.gestaovendas.service.VendaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<VendaRespondeDTO>> getVendaByIdCliente(@PathVariable Long id){
        return new ResponseEntity<>(vendaService.getVendaByIdCliente(id), HttpStatus.OK);
    }
    @GetMapping("/getVendaByNomeCliente")
    public ResponseEntity<List<VendaRespondeDTO>> getVendaByNomeCliente(@RequestParam String nome){
        return new ResponseEntity<>(vendaService.getVendaByNomeCliente(nome), HttpStatus.OK);
    }
}