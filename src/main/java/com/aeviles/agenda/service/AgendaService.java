package com.aeviles.agenda.service;

import com.aeviles.agenda.domain.entity.Agenda;
import com.aeviles.agenda.domain.entity.Evento;
import com.aeviles.agenda.domain.repository.AgendaRepository;
import com.aeviles.agenda.exceptional.BussinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //toda vez que houver algum problema na transação ele não salve no banco
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final EventoService eventoService;

    public List<Agenda> findAll(){
        return  agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id){
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda){
        //TODO: para validar as regras de negócio
        //1 -validar se paciente existe
        //aqui existe o paciente
        Optional<Evento> optionalPaciente = eventoService.findById(agenda.getEvento().getId());//dentro de agenda temos o Paciente,então pegamos o getPaciente e pegamos o id

        if(optionalPaciente.isEmpty()){
            throw new BussinessException("Paciente não encontrado");
        }
        //2-validar o horario

        Optional<Agenda> byHorario = agendaRepository.findByHorario(agenda.getHorario());

        if(byHorario.isPresent()){ //se horario existir dá a Bussines exception
            throw new BussinessException("Já existe agendamento para esse horário");
        }

        //se passar da Bussiness exception
        agenda.setEvento(optionalPaciente.get());//setar o paciente do optionalPaciente
        agenda.setDatacriacao(LocalDateTime.now());//ele pega o horario atual e seta

        return agendaRepository.save(agenda);

    }







}
