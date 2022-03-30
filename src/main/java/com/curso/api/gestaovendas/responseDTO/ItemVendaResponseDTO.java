package com.curso.api.gestaovendas.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("Dados de saida dos Intens de uma Venda")
public class ItemVendaResponseDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long idItemVenda;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço Vendido")
    private Double precoVendido;

    @ApiModelProperty(value = "ID do Produto")
    private Long idProduto;

    @ApiModelProperty(value = "Descrição do Produto")
    private String descricaoProduto;
}
