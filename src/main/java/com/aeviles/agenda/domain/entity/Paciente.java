package com.aeviles.agenda.domain.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paciente")
@Entity
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//quem gerencia esses valores é o banco
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;

}
