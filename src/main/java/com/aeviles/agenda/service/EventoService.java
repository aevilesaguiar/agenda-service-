package com.aeviles.agenda.service;

import com.aeviles.agenda.domain.entity.Evento;
import com.aeviles.agenda.domain.repository.EventoRepository;
import com.aeviles.agenda.exceptional.BussinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //toda vez que houver algum problema na transação ele não salva no banco
@RequiredArgsConstructor//ele cria um construtor , com todas as dependencias,  e a variavel deve ser declarada com final(quer dizer que você não pode atribuir valor duas vezes à variáve)
public class EventoService {

    //dessa forma eu já tenho o controle de depencia passada para o Spring
    private final EventoRepository repository;


    //eu posso usar para salvar e alterar um paciente, ele vai saber se tiver uma id ele vai ter que alterar e senão tiver ele vai ter que salvar
    public Evento salvar(Evento paciente){
        //TODO para validar se o cpf não existe

        boolean existeCpf = false;
        //buscar um paciente por cpf
        Optional<Evento> optionalPaciente = repository.findByCpf(paciente.getCpf());

        if(optionalPaciente.isPresent()){

            //se o id que estiver no paramento Paciente for diferente do cpf da base abaixo quer dizer que alguém está cadastrando um cpf que já existe
            if(!optionalPaciente.get().getId().equals(paciente.getId())){
                existeCpf=true;
            }
        }

        if(existeCpf){
            throw new RuntimeException("Cpf já cadastrado");
        }


        return repository.save(paciente);

    }

    public Evento alterar(Long id, Evento paciente){
        Optional<Evento> optionalPaciente =this.findById(id);

        if(optionalPaciente.isEmpty()){
            throw new BussinessException("Paciente não cadastrado");
        }
        paciente.setId(id);
        return salvar(paciente);
    }


    public List<Evento> findAll(){

        return repository.findAll();

    }

    public Optional<Evento> findById(Long id){

        return  repository.findById(id);
    }

    public void deletar(Long id){
            repository.deleteById(id);
    }







}
