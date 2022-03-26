package com.curso.api.gestaovendas.exception.entidadesEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntidadesMsgException {
    CATEGORIA("Caregoria"),
    CLIENTE("Cliente"),
    ITENVENDA("Item da venda"),
    PRODUTO("Produto"),
    VENDA("Venda");

    private String entidade;
}
