package com.curso.api.gestaovendas.responseDTO;

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
}
