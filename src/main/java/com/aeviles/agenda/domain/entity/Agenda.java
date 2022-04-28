package com.aeviles.agenda.domain.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")//esse padrão é o da tabela()
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDateTime datacriacao;


    @ManyToOne//para cada vez que eu registro uma agenda nós temos um evento
    private Evento evento;




}
