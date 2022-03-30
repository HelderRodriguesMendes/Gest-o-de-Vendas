package com.curso.api.gestaovendas.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("Dados de saida das vendas de um Cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteVendaResponseDTO implements Serializable {

    @ApiModelProperty(value = "ID do Cliente")
    private Long id;

    @ApiModelProperty(value = "Nome do Cliente")
    private String nomeCliente;

    @ApiModelProperty(value = "Vendas do cliente")
    private List<VendaResponseDTO> vendas;
}
