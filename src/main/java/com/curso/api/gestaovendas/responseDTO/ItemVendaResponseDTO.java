package com.curso.api.gestaovendas.responseDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemVendaResponseDTO implements Serializable {

    private Long idItemVenda;
    private Integer quantidade;
    private Double precoVendido;
    private Long idProduto;
    private String descricaoProduto;
}
