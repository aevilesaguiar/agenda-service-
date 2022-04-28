package com.aeviles.agenda.api.mapper;

import com.aeviles.agenda.api.request.AgendaRequest;
import com.aeviles.agenda.api.response.AgendaResponse;
import com.aeviles.agenda.domain.entity.Agenda;
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
        return mapper.map(request, Agenda.class);//eu quero transforma essa request em Evento.class
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toEventoResponseList(List<Agenda> agenda) {
        return agenda.stream()//percorrer todos os eventos
                .map(this::toAgendaResponse)//this a propria classe agenda mapper,
                .collect(Collectors.toList());//depois que ele fizer acima ele coleta tudo para uma lista
        //o final é uma lista de agenda
    }
}
