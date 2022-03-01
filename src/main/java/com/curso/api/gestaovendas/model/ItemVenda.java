package com.curso.api.gestaovendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_venda")
@Data
public class ItemVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_vendido")
    private Double precoVendido;

    @ManyToOne(cascade={CascadeType.ALL, CascadeType.PERSIST})
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_venda", referencedColumnName = "id")
    private Venda venda;
}
