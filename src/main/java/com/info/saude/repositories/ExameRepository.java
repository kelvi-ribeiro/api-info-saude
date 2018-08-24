package com.info.saude.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
	
	@Transactional(readOnly=true)	
	@Query("SELECT obj FROM Exame obj WHERE obj.paciente.id = :idPaciente")
	List<Exame> findAllByPacienteId(@Param("idPaciente") Integer idPaciente);
}