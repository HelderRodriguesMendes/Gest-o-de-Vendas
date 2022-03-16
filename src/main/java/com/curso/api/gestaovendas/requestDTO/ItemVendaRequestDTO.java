package com.curso.api.gestaovendas.requestDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemVendaRequestDTO implements Serializable {

    private Long idProduto;
    private Integer quantidade;
    private Double precoVendido;
}
