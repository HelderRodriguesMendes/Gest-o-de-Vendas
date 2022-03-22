package com.curso.api.gestaovendas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
@Data
@NoArgsConstructor
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne(cascade={CascadeType.ALL, CascadeType.PERSIST})
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    public Venda(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
    }
}
