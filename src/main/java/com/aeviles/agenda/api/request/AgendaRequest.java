package com.aeviles.agenda.api.request;


import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

    @NotBlank
    private String descricao;

    @NotNull
    @Future //não deixa fazer agendamento para datas passadas
    private LocalDateTime horario;

    @NotNull
    private Long pacienteId;




}
