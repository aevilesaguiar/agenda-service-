package com.aeviles.agenda.service;

import com.aeviles.agenda.domain.entity.Agenda;
import com.aeviles.agenda.domain.entity.Evento;
import com.aeviles.agenda.domain.repository.AgendaRepository;
import com.aeviles.agenda.exceptional.BussinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @InjectMocks
    AgendaService service;

    //Mockar o EventoService e o  AgendaRepository

    @Mock
    EventoService eventoService;
    @Mock
    AgendaRepository agendaRepository;

    @Captor
    ArgumentCaptor <Agenda> agendaArgumentCaptor;


    /**
     * Organizando o teste
     *          //setup
     *         //Arrange
     *
     *
     *         //teste
     *         //Acttion
     *
     *
     *
     *         //validações
     *         //Assertions
     */

    @Test
    @DisplayName("Deve salvar agendamento com sucesso")
    void salverComSuccess() {


        //Arrange - Processo para fazer o teste
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição do agendamento");
        agenda.setHorario(LocalDateTime.now());

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNome("Evento");


        agenda.setEvento(evento);

        //Vou emular o retorno de   Optional<Evento> optionalEvento = "agendaService.findById(agenda.getEvento().getId());"  da classe agendaService
        Mockito.when(eventoService.findById(agenda.getEvento().getId())).thenReturn(Optional.of(evento));

        //vou emular  Optional<Agenda> byHorario = agendaRepository.findByHorario(agenda.getHorario());
        Mockito.when(agendaRepository.findByHorario(now)).thenReturn(Optional.empty());

        //action
        service.salvar(agenda);

        //Assertion

        //testes de iteração
        Mockito.verify(eventoService).findById(agenda.getEvento().getId());
        Mockito.verify(agendaRepository).findByHorario(now);


        Mockito.verify(agendaRepository).save(agendaArgumentCaptor.capture());

        //verifica o estado da agenda nesse momento agendaRepository.save(agenda);
        Agenda agendaSalva = agendaArgumentCaptor.getValue();

        Assertions.assertThat(agendaSalva.getEvento()).isNotNull();
        Assertions.assertThat(agendaSalva.getDescricao()).isNotNull();
        Assertions.assertThat(agendaSalva.getDatacriacao()).isNotNull();

    }


    @Test
    @DisplayName("Não deve salvar agendamento sem evento")
     void salverErrorEventNaoEncontre(){



        //Arrange - Processo para fazer o teste
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição do agendamento do evento");
        agenda.setHorario(now);

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNome("Evento");


        agenda.setEvento(evento);


        Mockito.when(eventoService.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        BussinessException bussinessException= assertThrows(BussinessException.class, ()->{
            service.salvar(agenda);
        });


        Assertions.assertThat(bussinessException.getMessage()).isEqualTo("Evento não encontrado");



    }



}