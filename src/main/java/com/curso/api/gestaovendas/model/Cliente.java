package com.curso.api.gestaovendas.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
@Data
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Telefone")
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "ativo")
    private Boolean ativo;

    @Embedded
    private Endereco endereco;
}
