package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.dto.CategoriaResponseDTO;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation(value = "Salvar Categoria", nickname = "salvar")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.salvar(categoria), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todas categorias", nickname = "listarCategorias")
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategoria(){
        return new ResponseEntity<>(categoriaService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar categoria por ID", nickname = "bucarCategoriaId")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id){
        CategoriaResponseDTO optionalCategoria = categoriaService.getById(id);
         return new ResponseEntity<>(optionalCategoria, HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar categoria por nome", nickname = "bucarCategoriaNome")
    @GetMapping("/getNome")
    public ResponseEntity<List<CategoriaResponseDTO>> getByNome(@RequestParam String nome){
        return new ResponseEntity<>(categoriaService.findByNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar categoria", nickname = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return new ResponseEntity<>(categoriaService.atualizar(categoria), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar uma categoria", nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }
}
