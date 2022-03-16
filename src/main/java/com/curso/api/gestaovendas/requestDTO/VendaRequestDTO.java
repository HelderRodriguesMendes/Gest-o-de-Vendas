package com.curso.api.gestaovendas.requestDTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class VendaRequestDTO implements Serializable {

        private LocalDate data;
        private List<ItemVendaRequestDTO> itemVendaRequestDTOS;
}
