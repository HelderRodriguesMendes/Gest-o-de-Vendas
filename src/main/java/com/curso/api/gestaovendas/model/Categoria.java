package com.curso.api.gestaovendas.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
@Data
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    @Column(name = "nome")
    private String nome;
}
