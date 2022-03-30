package com.curso.api.gestaovendas.requestDTO;

import com.curso.api.gestaovendas.model.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("Dados de entrada de um Produto")
public class ProdutoRequestDTO implements Serializable {

    @ApiModelProperty(value = "Código")
    private Long id;

    @ApiModelProperty(value = "Descrição")
    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço de custo")
    @NotNull(message = "Preço custo")
    private Double precoCusto;

    @ApiModelProperty(value = "Preço de venda")
    @NotNull(message = "Preço venda")
    private Double precoVenda;

    @ApiModelProperty(value = "Observação")
    @Length(max = 500, message = "Observação")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    @NotNull(message = "Código da categoria")
    @ManyToOne
    private Categoria categoria;
}
