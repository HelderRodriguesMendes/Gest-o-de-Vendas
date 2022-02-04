package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.requestDTO.CategoriaRequestDTO;
import com.curso.api.gestaovendas.responseDTO.CategoriaResponseDTO;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.service.CategoriaService;
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

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    Convert convert = new Convert();

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation(value = "Salvar Categoria", nickname = "salvar")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return new ResponseEntity<>(categoriaService.salvar(convert.toCategoria(categoriaRequestDTO)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todas categorias", nickname = "listarCategorias")
    @GetMapping("/getAll")
    public ResponseEntity<Page<CategoriaResponseDTO>> getAllCategoria(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return new ResponseEntity<>(categoriaService.getAll(pageable), HttpStatus.OK);
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
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        categoriaRequestDTO.setId(id);
        return new ResponseEntity<>(categoriaService.atualizar(convert.toCategoria(categoriaRequestDTO)), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar uma categoria", nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }
}
