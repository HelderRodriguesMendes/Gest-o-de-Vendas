package com.curso.api.gestaovendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_venda")
@Data
@NoArgsConstructor
public class ItemVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_vendido")
    private Double precoVendido;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_venda", referencedColumnName = "id")
    private Venda venda;

    public ItemVenda(Integer quantidade, Double precoVendido, Produto produto, Venda venda) {
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.produto = produto;
        this.venda = venda;
    }
}
