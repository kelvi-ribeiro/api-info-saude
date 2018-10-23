package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.PacienteLinhaCuidado;

@Repository
public interface PacienteLinhaCuidadoRepository extends JpaRepository<PacienteLinhaCuidado, Integer> {	
	
}