package com.curso.api.gestaovendas.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CategoriaRequestDTO implements Serializable {

    @ApiModelProperty(value = "Código")
    private Long id;

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;
}
