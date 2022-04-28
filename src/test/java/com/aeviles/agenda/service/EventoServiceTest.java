package com.aeviles.agenda.service;

import com.aeviles.agenda.domain.entity.Evento;
import com.aeviles.agenda.domain.repository.EventoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository repository;

    EventoService eventoService;

    @BeforeEach
    void initUsecase(){
        eventoService =new EventoService(repository);
    }

    @Test
    @DisplayName("deve salvar paciente com sucesso")
    public void savedPaciente_Sucess(){
        Evento paciente= new Evento();

        paciente.setNome("Aeviles");
        paciente.setSobrenome("Aguiar");
        paciente.setEmail("aeviles@email.com");
        paciente.setCpf("123456789-5");


        Mockito.when(repository.save(Mockito.any(Evento.class))).thenReturn(paciente);


        Evento savedPaciente = repository.save(paciente);

        Assertions.assertThat(savedPaciente.getNome()).isNotNull();
    }


    @Test
    @DisplayName("paciente existe no banco de dados")
    public void paciente_exists_in_db_sucess(){
        Evento paciente= new Evento();

        paciente.setNome("Aeviles");
        paciente.setSobrenome("Aguiar");
        paciente.setEmail("aeviles@email.com");
        paciente.setCpf("123456789-5");

        List<Evento> pacientes= new ArrayList<>();
        pacientes.add(paciente);


        Mockito.when(repository.findAll()).thenReturn(pacientes);

        List<Evento> pacienteList = eventoService.findAll();
        Assertions.assertThat(pacienteList.size()).isGreaterThan(0);


    }





}