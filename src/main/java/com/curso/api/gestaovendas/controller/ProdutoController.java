package com.curso.api.gestaovendas.controller;

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
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.salvar(produto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todos os produtos", nickname = "listarProdutos")
    @GetMapping("/getAll")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return new ResponseEntity<>(produtoService.getAllProdutos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por descrição", nickname = "bucarProdutoPorDescrição")
    @GetMapping("/getByNome")
    public ResponseEntity<List<Produto>> getByNome(@RequestParam String nome){
        return new ResponseEntity<>(produtoService.getByNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por id", nickname = "bucarProdutoPorID")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        Optional<Produto>produtoOptional = produtoService.getIdProduto(id);
        return produtoOptional.map(produto -> new ResponseEntity<>(produto, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Atualizar produto", nickname = "atualizarProduto")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto){
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
