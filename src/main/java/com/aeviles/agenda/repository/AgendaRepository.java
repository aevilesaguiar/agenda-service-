package com.aeviles.agenda.repository;

import com.aeviles.agenda.domain.entity.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
