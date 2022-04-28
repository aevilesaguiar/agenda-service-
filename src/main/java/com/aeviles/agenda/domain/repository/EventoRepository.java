package com.aeviles.agenda.domain.repository;

import com.aeviles.agenda.domain.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    //faz uma querie procurarndo pelo cpf
    Optional<Evento> findByCpf(String cpf);
}
