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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    Convert convert = new Convert();
    List<Categoria> categorias = new ArrayList<>();
    Categoria categoria = new Categoria();

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation(value = "Salvar Categoria", nickname = "salvar")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        categoria = categoriaService.salvar(convert.toCategoria(categoriaRequestDTO));
        return new ResponseEntity<>(convert.toCategoriaResponseDTO(categoria), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar todas categorias", nickname = "listarCategorias")
    @GetMapping("/getAll")
    public ResponseEntity<Page<CategoriaResponseDTO>> getAllCategoria(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        List<CategoriaResponseDTO> categoriaResponseDTOS = categoriaService.getAll(pageable).stream().map(categoria ->
            convert.toCategoriaResponseDTO(categoria)).collect(Collectors.toList());
        return new ResponseEntity<>(new PageImpl<>(categoriaResponseDTOS), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar categoria por ID", nickname = "bucarCategoriaId")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id){
        CategoriaResponseDTO optionalCategoria = convert.toCategoriaResponseDTO(categoriaService.getById(id));
         return new ResponseEntity<>(optionalCategoria, HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar categoria por nome", nickname = "bucarCategoriaNome")
    @GetMapping("/getNome")
    public ResponseEntity<List<CategoriaResponseDTO>> getByNome(@RequestParam String nome){
        categorias = categoriaService.findByNome(nome);
        return new ResponseEntity<>(categorias.stream().map(categoria ->
            convert.toCategoriaResponseDTO((Categoria) categoria)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar categoria", nickname = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        categoriaRequestDTO.setId(id);
        categoria = categoriaService.atualizar(convert.toCategoria(categoriaRequestDTO));
        return new ResponseEntity<>(convert.toCategoriaResponseDTO(categoria), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar uma categoria", nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }
}
