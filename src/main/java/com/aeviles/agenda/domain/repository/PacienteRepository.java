package com.aeviles.agenda.domain.repository;

import com.aeviles.agenda.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    //faz uma querie procurarndo pelo cpf
    Optional<Paciente> findByCpf(String cpf);
}
