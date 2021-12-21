package com.curso.api.gestaovendas.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Quantidade")
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull(message = "Preço custo")
    @Column(name = "preco_custo")
    private Double precoCusto;

    @NotNull(message = "Preço venda")
    @Column(name = "preco_venda")
    private Double precoVenda;

    @Length(max = 500, message = "Observação")
    @Column(name = "observacao")
    private String observacao;

    @NotNull(message = "Código da categoria")
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
}
