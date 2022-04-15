package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.requestDTO.VendaRequestDTO;
import com.curso.api.gestaovendas.responseDTO.ClienteVendaResponseDTO;
import com.curso.api.gestaovendas.responseDTO.VendaResponseDTO;
import com.curso.api.gestaovendas.service.ClienteService;
import com.curso.api.gestaovendas.service.ItemVendaService;
import com.curso.api.gestaovendas.service.VendaService;
import com.curso.api.gestaovendas.util.Convert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private ClienteService clienteService;

    Convert convert = new Convert();
    List<Venda> vendas = new ArrayList<>();

    @ApiOperation(value = "Salvar Venda", nickname = "salvarVenda")
    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<VendaResponseDTO> salvar(@PathVariable Long idCliente, @Valid @RequestBody VendaRequestDTO vendaRequestDTO){
        Venda vendaSalva = vendaService.salvar(idCliente, vendaRequestDTO);
        return new ResponseEntity<>(convert.toVendaResponseDTO(vendaSalva, vendaRequestDTO.getItemVendaRequestDTOS(), itemVendaService), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Bucar todas as vendas", nickname = "getAllVendas")
    @GetMapping("/getAll")
    public ResponseEntity<List<VendaResponseDTO>> getAllVendas(){
        List<Venda> vendas = vendaService.getAllVendas();
        return new ResponseEntity<>(convert.toListVendaResponseDTO(vendas, itemVendaService), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar vendas de um cliente", nickname = "getVendaByIdCliente")
    @GetMapping("/vendaByIdCliente/{id}")
    public ResponseEntity<ClienteVendaResponseDTO> getVendaByIdCliente(@PathVariable Long id){
        List<Venda> vendas = vendaService.findVendaByIdCliente(id);
        Cliente cliente = clienteService.getById(id);
        return new ResponseEntity<>(new ClienteVendaResponseDTO(cliente.getId(), cliente.getNome(),
            convert.toListVendaResponseDTO(vendas, itemVendaService)), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar uma venda por ID", nickname = "getVendaById")
    @GetMapping("/getVendaById/{id}")
    public ResponseEntity<VendaResponseDTO> getVendaById(@PathVariable Long id){
        Venda venda = vendaService.getVendaById(id);
        return new ResponseEntity<>(convert.toVendaResponseDTO(venda, itemVendaService), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar Vendas por uma data", nickname = "getVendaByData")
    @GetMapping("/getVendaByData")
    public ResponseEntity<List<VendaResponseDTO>> getVendaByData(@RequestParam String data){
        vendas = vendaService.getVendaByData(data);
        return new ResponseEntity<>(convert.toListVendaResponseDTO(vendas, itemVendaService), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar venda", nickname = "atualizarVenda")
    @PutMapping("/{idVenda}/cliente/{idCliente}")
    public ResponseEntity<VendaResponseDTO> atualizar(@PathVariable Long idVenda, @PathVariable Long idCliente, @RequestBody VendaRequestDTO vendaRequestDTO){
        List<ItemVenda> itemVendasAtualizadas = itemVendaService.atualizar(vendaRequestDTO.getItemVendaRequestDTOS(), idVenda);
        Venda vendaAtualizada = vendaService.atualizar(idVenda, idCliente, vendaRequestDTO);
        return new ResponseEntity<>(convert.toVendaResponseDTO(itemVendasAtualizadas, vendaAtualizada), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar uma venda", nickname = "deletarVenda")
    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        vendaService.deletar(id);
    }
}