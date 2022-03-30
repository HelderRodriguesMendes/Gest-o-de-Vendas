package com.curso.api.gestaovendas.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@ApiModel("Dados de entrada de uma Venda")
public class VendaRequestDTO implements Serializable {

        @ApiModelProperty(value = "Data")
        @NotNull(message = "Data")
        private LocalDate data;

        @ApiModelProperty(value = "Itens da venda")
        @NotNull(message = "Itens da venda")
        private List<ItemVendaRequestDTO> itemVendaRequestDTOS;
}
