package com.aeviles.agenda.domain.service;

import com.aeviles.agenda.domain.entity.Paciente;
import com.aeviles.agenda.domain.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //toda vez que houver algum problema na transação ele não salve no banco
@RequiredArgsConstructor//ele cria um construtor , com todas as dependencias,  e a variavel deve ser declarada com final(quer dizer que você não pode atribuir valor duas vezes à variáve)
public class PacienteService {

    //dessa forma eu já tenho o controle de depencia passada para o Spring
    private final PacienteRepository repository;

    public Paciente salvar( Paciente paciente){
        //TODO para validar se o cpf não existe


        return repository.save(paciente);

    }

    public List<Paciente> findAll(){

        return repository.findAll();

    }

    public Optional<Paciente> findById(Long id){

        return  repository.findById(id);
    }

    public void deletar(Long id){
            repository.deleteById(id);
    }







}
