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
    private Long id;
    private String descricao;
    private int quantidade;

    @Column(name = "preco_custo")
    private double precoCusto;

    @Column(name = "preco_venda")
    private double precoVenda;
    private String observacao;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    //@JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
}
