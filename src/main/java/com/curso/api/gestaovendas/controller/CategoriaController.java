package com.curso.api.gestaovendas.controller;

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
import java.util.Optional;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation(value = "Salvar Categoria", nickname = "salvar")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.salvar(categoria), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todas categorias", nickname = "listarCategorias")
    @GetMapping("/getAll")
    public ResponseEntity<List<Categoria>> getAllCategoria(){
        return new ResponseEntity<List<Categoria>>(categoriaService.getAllCategoria(), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar categoria por ID", nickname = "bucarCategoriaId")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        Optional<Categoria> optionalCategoria = categoriaService.getById(id);
        return optionalCategoria.isPresent() ? new ResponseEntity<Categoria>(optionalCategoria.get(), HttpStatus.OK) :
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Bucar categoria por Nome", nickname = "bucarCategoriaNome")
    @GetMapping("/getNome")
    public ResponseEntity<List<Categoria>> getByNome(@RequestParam String nome){
        return new ResponseEntity<List<Categoria>>(categoriaService.findByNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar categoria", nickname = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return new ResponseEntity<Categoria>(categoriaService.atualizar(categoria), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar uma categoria", nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }
}
