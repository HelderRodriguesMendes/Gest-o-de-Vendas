package com.curso.api.gestaovendas.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaResponseDTO implements Serializable {
    private Long id;
    private LocalDate data;
    private List<ItemVendaResponseDTO> itemVendaDTOS;
}
