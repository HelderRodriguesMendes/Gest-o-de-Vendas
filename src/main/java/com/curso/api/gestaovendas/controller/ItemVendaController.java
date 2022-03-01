package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.responseDTO.ItemVendaResponseDTO;
import com.curso.api.gestaovendas.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemVenda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ItemVendaResponseDTO>> getItemVendaByIdVenda(@PathVariable Long id){
        return new ResponseEntity<>(itemVendaService.getItemVendaByIdVenda(id), HttpStatus.OK);
    }
}
