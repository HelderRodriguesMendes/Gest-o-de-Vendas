package com.curso.api.gestaovendas.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel("Dados de saida de uma Categoria e seus Produtos")
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProdutosResponseDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Produtos")
    private List<ProdutoResponseDTO> produtoResponseDTOS;
}
