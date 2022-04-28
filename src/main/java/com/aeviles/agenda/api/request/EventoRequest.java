package com.aeviles.agenda.api.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

//essa classe seria o mesmo que o DTO = request - solicitação
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequest {
    @NotBlank(message = "Nome do Evento é obrigatório")
    private String nomeEvento;

    @NotBlank(message = "Nome do Palestrante é obrigatorio")
    private String nome;

    @NotBlank(message = "Sobrenome do Paciente é obrigatório")
    private String sobrenome;

    private String email;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
}
