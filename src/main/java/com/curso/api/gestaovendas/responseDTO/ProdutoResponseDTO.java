package com.curso.api.gestaovendas.responseDTO;

import com.curso.api.gestaovendas.model.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("Dados de saida de um Produto")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Descrição")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço de custo")
    private Double precoCusto;

    @ApiModelProperty(value = "Preço de venda")
    private Double precoVenda;

    @ApiModelProperty(value = "Observação")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    private Categoria categoria;
}
