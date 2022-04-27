package com.aeviles.agenda.api.controller;

import com.aeviles.agenda.api.mapper.PacienteMapper;
import com.aeviles.agenda.api.request.PacienteRequest;
import com.aeviles.agenda.api.response.PacienteResponse;
import com.aeviles.agenda.domain.entity.Paciente;
import com.aeviles.agenda.domain.service.PacienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor //criar injeção de dependencia
public class PacienteController {

    //ao invés da annottatio @Autowired eu estou usando a anottation @RequiredArgsConstructor
    private final PacienteService service;
    private final PacienteMapper mapper;


    @PostMapping  //quem chamar /paciente e fizer um post , vai chamar salvar
    public ResponseEntity<PacienteResponse> salvar(@Valid  @RequestBody PacienteRequest pacienteRequest) {

        Paciente paciente = mapper.toPaciente(pacienteRequest);

        Paciente pacienteSalvo = service.salvar(paciente);

        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);// CREATED(201, HttpStatus.Series.SUCCESSFUL, "Created"),
        //porque estou mandando salvar
    }

    @GetMapping // quando eu chamar o /paciente e fizer um get , vai listar todos
    public ResponseEntity<List<PacienteResponse>> findAll() {

        List<Paciente> pacientes = service.findAll();
        List<PacienteResponse> pacienteResponses = mapper.toPacienteResponseList(pacientes);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorIdl(@PathVariable Long id) {

        Optional<Paciente> optionalPaciente = service.findById(id);

        if (optionalPaciente.isEmpty()) {//se estiver vazio o paciente não existe (não encontrado-not Found)
            return ResponseEntity.notFound().build();
        }

        //se ele não for vazio, ele pega o id e retorna o status 200 - ok
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(optionalPaciente.get()));
    }

    //o que altera
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest) {//Paciente é o id, se existe um id ele salva um novo
        Paciente paciente = mapper.toPaciente(pacienteRequest);
        Paciente pacienteSalvo = service.alterar(id, paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}