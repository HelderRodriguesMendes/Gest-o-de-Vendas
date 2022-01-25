package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.dto.ProdutoResponseDTO;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @ApiOperation(value = "Salvar um produto", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.salvar(produto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todos os produtos", nickname = "listarProdutos")
    @GetMapping("/getAll")
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutos(){
        return new ResponseEntity<>(produtoService.getAllProdutos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por descrição", nickname = "bucarProdutoPorDescrição")
    @GetMapping("/getByNome")
    public ResponseEntity<List<ProdutoResponseDTO>> getByNome(@RequestParam String nome){
        return new ResponseEntity<>(produtoService.getByNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por id", nickname = "bucarProdutoPorID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Long id){
        return  new ResponseEntity<>(produtoService.getIdProduto(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar produto", nickname = "atualizarProduto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody Produto produto){
        produto.setId(id);
        return new ResponseEntity<>(produtoService.atualizar(produto), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar um produto", nickname = "deletarProduto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        produtoService.deletar(id);
    }
}
