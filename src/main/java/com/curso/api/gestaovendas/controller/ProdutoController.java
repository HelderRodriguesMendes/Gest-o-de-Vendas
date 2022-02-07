package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.requestDTO.ProdutoRequestDTO;
import com.curso.api.gestaovendas.responseDTO.ProdutoResponseDTO;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.service.ProdutoService;
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

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    Convert convert = new Convert();

    @ApiOperation(value = "Salvar um produto", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO){
        return new ResponseEntity<>(produtoService.salvar(convert.toProduto(produtoRequestDTO)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todos os produtos", nickname = "listarProdutos")
    @GetMapping("/getAll")
    public ResponseEntity<Page<ProdutoResponseDTO>> getAllProdutos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<>(produtoService.getAllProdutos(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por descrição", nickname = "bucarProdutoPorDescrição")
    @GetMapping("/getByNome")
    public ResponseEntity<List<ProdutoResponseDTO>> getByNome(@RequestParam String nome){
        return new ResponseEntity<>(produtoService.getByNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por id", nickname = "bucarProdutoPorID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Long id){
        return  new ResponseEntity<>(produtoService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar produto", nickname = "atualizarProduto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO){
        produtoRequestDTO.setId(id);
        return new ResponseEntity<>(produtoService.atualizar(convert.toProduto(produtoRequestDTO)), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar um produto", nickname = "deletarProduto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        produtoService.deletar(id);
    }
}
