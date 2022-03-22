package com.curso.api.gestaovendas.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteVendaResponseDTO implements Serializable {
    private Long id;
    private String nomeCliente;
    private List<VendaResponseDTO> vendas;
}
