package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Interacao;

@Repository
public interface InteracaoRepository extends JpaRepository<Interacao, Integer> {
	
	@Transactional(readOnly = true)	
	Interacao findByPacienteIdAndMensagemId(
				@Param("idPaciente") Integer idPaciente,
				@Param("idMensagem") Integer idMensagem);
}