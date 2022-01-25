package com.curso.api.gestaovendas.dto;

import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.model.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("Produto retorno DTO")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long id;

    @ApiModelProperty(value = "Descrição")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço de custo")
    private Double precoCusto;

    @ApiModelProperty(value = "Preço de venda")
    private Double precoVenda;

    @ApiModelProperty(value = "Observação")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    private Categoria categoria;
}
