package com.aeviles.agenda.api.mapper;

import com.aeviles.agenda.api.request.AgendaRequest;
import com.aeviles.agenda.api.request.PacienteRequest;
import com.aeviles.agenda.api.response.AgendaResponse;
import com.aeviles.agenda.api.response.PacienteResponse;
import com.aeviles.agenda.domain.entity.Agenda;
import com.aeviles.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // anotado como componente
@RequiredArgsConstructor  //senão houvesse essa anotação teríamos que gerar o construtor de todos os metodos
public class AgendaMapper {
    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest request) {
        return mapper.map(request, Agenda.class);//eu quero transforma essa request em Paciente.class
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toPacienteResponseList(List<Agenda> agenda) {
        return agenda.stream()//percorrer todos os paciente
                .map(this::toAgendaResponse)//this a propria classe agenda mapper,
                .collect(Collectors.toList());//depois que ele fizer acima ele coleta tudo para uma lista
        //o final é uma lista de agenda
    }
}
