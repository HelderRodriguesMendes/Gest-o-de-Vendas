package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.responseDTO.VendasGetAllResponseDTO;
import com.curso.api.gestaovendas.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;


    @ApiOperation(value = "Listar todas as Vendas", nickname = "listarVendas")
    @GetMapping("/getAll")
    public ResponseEntity<Page<VendasGetAllResponseDTO>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<>(vendaService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Listar todas as Vendas de um cliente", nickname = "listarVendasCliente")
    @GetMapping("/getVendasByCliente")
    public ResponseEntity<List<VendasGetAllResponseDTO>>getVendasByCliente(@RequestParam String nome){
        return new ResponseEntity<>(vendaService.getVendasByCliente(nome), HttpStatus.OK);
    }
}