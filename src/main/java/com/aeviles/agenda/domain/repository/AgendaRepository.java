package com.aeviles.agenda.domain.repository;

import com.aeviles.agenda.domain.entity.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
