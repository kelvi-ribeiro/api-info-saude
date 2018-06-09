package com.fiocruz.comunicacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiocruz.comunicacao.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	
}