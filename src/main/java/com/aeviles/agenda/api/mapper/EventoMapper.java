package com.aeviles.agenda.api.mapper;

import com.aeviles.agenda.api.request.EventoRequest;
import com.aeviles.agenda.api.response.EventoResponse;
import com.aeviles.agenda.domain.entity.Evento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // anotado como componente
@RequiredArgsConstructor  //senão houvesse essa anotação teríamos que gerar o construtor de todos os metodos
public class EventoMapper {

    private final ModelMapper mapper;

    public Evento toEvento(EventoRequest request) {
        return mapper.map(request, Evento.class);//eu quero transforma essa request em Evento.class
    }

    public EventoResponse toEventoResponse(Evento evento) {
        return mapper.map(evento, EventoResponse.class);
    }

    public List<EventoResponse> toEventoResponseList(List<Evento> eventos) {
        return eventos.stream()//percorrer todos os eventos
                .map(this::toEventoResponse)//this a propria classe evento mapper,
                .collect(Collectors.toList());//depois que ele fizer acima ele coleta tudo para uma lista
                //o final é uma lista de evento 
    }
}
