package com.curso.api.gestaovendas.responseDTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class VendaRespondeDTO implements Serializable {
    private Long id;
    private LocalDate data;
    private List<ItemVendaResponseDTO> itemVendaDTOS;
}
