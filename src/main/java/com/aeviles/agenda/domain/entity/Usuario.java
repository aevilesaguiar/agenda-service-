package com.aeviles.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "usuario")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    //os atributos podem ser validados direto aqui

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

}
