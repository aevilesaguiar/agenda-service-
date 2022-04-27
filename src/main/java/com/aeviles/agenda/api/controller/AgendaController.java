package com.aeviles.agenda.api.controller;


import com.aeviles.agenda.api.mapper.AgendaMapper;
import com.aeviles.agenda.api.request.AgendaRequest;
import com.aeviles.agenda.api.response.AgendaResponse;
import com.aeviles.agenda.domain.entity.Agenda;
import com.aeviles.agenda.domain.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor //criar injeção de dependencia
public class AgendaController {

    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos(){
        List<Agenda> agendaList = agendaService.findAll();
        List<AgendaResponse> agendaResponses = agendaMapper.toPacienteResponseList(agendaList);

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id){

        Optional<Agenda> optionalAgenda = agendaService.buscarPorId(id);
        if(optionalAgenda.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(optionalAgenda.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);


    }

    //post
    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest agendaRequest){

        //transforma de AgendaRequest para Agenda e depois de Agenda para AgendaResponse
        Agenda agenda = agendaMapper.toAgenda(agendaRequest);
        Agenda agendaSalva = agendaService.salvar(agenda);
        AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(agendaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);


    }



}
