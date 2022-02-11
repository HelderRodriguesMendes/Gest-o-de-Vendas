package com.curso.api.gestaovendas.responseDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VendasGetAllResponseDTO {

    private Long id;
    private LocalDate data;
    private ClienteReponseGetVendasDTO cliente;
}
