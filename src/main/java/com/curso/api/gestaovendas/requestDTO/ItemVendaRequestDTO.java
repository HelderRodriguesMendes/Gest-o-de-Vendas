package com.curso.api.gestaovendas.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("Dados de entrada dos Intens de uma Venda")
public class ItemVendaRequestDTO implements Serializable {

    @ApiModelProperty(value = "Id produto")
    @NotNull(message = "Id produto")
    private Long idProduto;

    @ApiModelProperty(value = "Qunatidade")
    @NotNull(message = "Qunatidade")
    @Min(value = 1, message = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço vendido")
    @NotNull(message = "Preço vendido")
    private Double precoVendido;
}
