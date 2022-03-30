package com.curso.api.gestaovendas.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("Dados de saida de uma Venda")
@AllArgsConstructor
public class VendaResponseDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Data da Venda")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da Venda")
    private List<ItemVendaResponseDTO> itemVendaDTOS;
}
