package com.curso.api.gestaovendas.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("Dados de saida de um Cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "Logradouro")
    private String logradouro;

    @ApiModelProperty(value = "Número")
    private Integer numero;

    @ApiModelProperty(value = "Complemento")
    private String complemento;

    @ApiModelProperty(value = "Bairro")
    private String bairro;

    @ApiModelProperty(value = "CEP")
    private String cep;

    @ApiModelProperty(value = "Cidade")
    private String cidade;

    @ApiModelProperty(value = "Estado")
    private String estado;
}
