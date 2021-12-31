package com.curso.api.gestaovendas.dto;

import com.curso.api.gestaovendas.model.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("Categoria retorno DTO")
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    public static CategoriaResponseDTO toDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
}
