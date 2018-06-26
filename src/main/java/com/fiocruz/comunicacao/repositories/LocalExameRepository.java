package com.fiocruz.comunicacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fiocruz.comunicacao.domain.LocalExame;

@Repository
public interface LocalExameRepository extends JpaRepository<LocalExame, Integer> {
	
	@Transactional(readOnly=true)	
	@Query("SELECT obj FROM LocalExame obj WHERE obj.paciente.id = :idPaciente")
	List<LocalExame> findLocaisExameByPacienteId(@Param("idPaciente") Integer idPaciente);
}