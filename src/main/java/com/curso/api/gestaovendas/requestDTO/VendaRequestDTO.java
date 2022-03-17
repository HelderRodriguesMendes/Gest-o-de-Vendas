package com.curso.api.gestaovendas.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class VendaRequestDTO implements Serializable {

        @ApiModelProperty(value = "Data")
        @NotNull(message = "Data")
        private LocalDate data;

        @ApiModelProperty(value = "Itens da venda")
        @NotNull(message = "Itens venda")
        private List<ItemVendaRequestDTO> itemVendaRequestDTOS;
}
