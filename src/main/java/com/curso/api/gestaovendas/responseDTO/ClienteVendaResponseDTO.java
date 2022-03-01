package com.curso.api.gestaovendas.responseDTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClienteVendaResponseDTO implements Serializable {
    private Long id;
    private String nomeCliente;
    private List<VendaRespondeDTO> vendas;
}
