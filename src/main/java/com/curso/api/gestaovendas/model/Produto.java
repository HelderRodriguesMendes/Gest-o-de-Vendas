package com.curso.api.gestaovendas.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_custo")
    private Double precoCusto;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne(cascade={CascadeType.ALL, CascadeType.PERSIST})
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
}
