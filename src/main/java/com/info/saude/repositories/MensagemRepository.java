package com.info.saude.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT m FROM Mensagem m,"
			+ "PacienteLinhaCuidado plc "
			+ "WHERE m.linhaCuidado.id = plc.linhaCuidado.id "
			+ "AND plc.paciente.id= :idPaciente "
			+ "OR m.geral=true "
			+ "OR m.paciente.id = :idPaciente "
			+ "ORDER BY m.id DESC")
	Page<Mensagem> findAllByPaciente(
			@Param("idPaciente") Integer idPaciente,
			@Param("pageRequest") Pageable pageRequest);
}