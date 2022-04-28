package com.aeviles.agenda.api.controller;

import com.aeviles.agenda.api.mapper.EventoMapper;
import com.aeviles.agenda.api.request.EventoRequest;
import com.aeviles.agenda.api.response.EventoResponse;
import com.aeviles.agenda.domain.entity.Evento;
import com.aeviles.agenda.service.EventoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
@RequiredArgsConstructor //criar injeção de dependencia
public class EventoController {

    //ao invés da annottatio @Autowired eu estou usando a anottation @RequiredArgsConstructor
    private final EventoService service;
    private final EventoMapper mapper;


    @PostMapping  //quem chamar /evento e fizer um post , vai chamar salvar
    public ResponseEntity<EventoResponse> salvar(@Valid  @RequestBody EventoRequest eventoRequest) {

        Evento evento = mapper.toEvento(eventoRequest);

        Evento eventoSalvo = service.salvar(evento);

        EventoResponse eventoResponse = mapper.toEventoResponse(eventoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoResponse);// CREATED(201, HttpStatus.Series.SUCCESSFUL, "Created"),
        //porque estou mandando salvar
    }

    @GetMapping // quando eu chamar o /evento e fizer um get , vai listar todos
    public ResponseEntity<List<EventoResponse>> findAll() {

        List<Evento> eventos = service.findAll();
        List<EventoResponse> eventoResponses = mapper.toEventoResponseList(eventos);

        return ResponseEntity.status(HttpStatus.OK).body(eventoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorIdl(@PathVariable Long id) {

        Optional<Evento> optionalEvento = service.findById(id);

        if (optionalEvento.isEmpty()) {//se estiver vazio o evento não existe (não encontrado-not Found)
            return ResponseEntity.notFound().build();
        }

        //se ele não for vazio, ele pega o id e retorna o status 200 - ok
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toEventoResponse(optionalEvento.get()));
    }

    //o que altera
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> alterar(@PathVariable Long id, @RequestBody EventoRequest eventoRequest) {//Eventoe é o id, se existe um id ele salva um novo
        Evento evento = mapper.toEvento(eventoRequest);
        Evento eventoSalvo1 = service.alterar(id, evento);
        EventoResponse eventoResponse = mapper.toEventoResponse(eventoSalvo1);

        return ResponseEntity.status(HttpStatus.OK).body(eventoResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}