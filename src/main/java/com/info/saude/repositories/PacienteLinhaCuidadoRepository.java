package com.info.saude.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.PacienteLinhaCuidado;

@Repository
public interface PacienteLinhaCuidadoRepository extends JpaRepository<PacienteLinhaCuidado, Integer> {
	
	@Transactional(readOnly = true)	
	List<PacienteLinhaCuidado> findByPacienteId(@Param("pacienteId") Integer pacienteId);

}