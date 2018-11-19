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
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT m FROM Mensagem  m "			
			+ "WHERE m.assunto LIKE CONCAT('%',:campoPesquisa,'%')"			
			+ "AND (m.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) ")
	Page<Mensagem> findByAssuntoAndLinhaCuidado(
			 @Param("campoPesquisa") String campoPesquisa,
			 @Param("pageRequest") Pageable pageRequest,
			 @Param("linhaCuidadoId") Integer linhaCuidadoId);
	
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT m FROM Mensagem  m "			
			+ "WHERE m.mensagem LIKE CONCAT('%',:campoPesquisa,'%')"			
			+ "AND (m.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) ")
	Page<Mensagem> findByMensagemAndLinhaCuidado(
			 @Param("campoPesquisa") String campoPesquisa,
			 @Param("pageRequest") Pageable pageRequest,
			 @Param("linhaCuidadoId") Integer linhaCuidadoId);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT m FROM Mensagem  m, "
			+ "ProfissionalSaude ps, "
			+ "Pessoa p "
			+ "WHERE m.profissionalSaude.id = ps.id "
			+ "AND ps.pessoa.id = p.id "
			+ "AND p.nome LIKE CONCAT('%',:campoPesquisa,'%')"			
			+ "AND (m.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) ")
	Page<Mensagem> findByProfissionalSaudeAndLinhaCuidado(
			 @Param("campoPesquisa") String campoPesquisa,
			 @Param("pageRequest") Pageable pageRequest,
			 @Param("linhaCuidadoId") Integer linhaCuidadoId);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT COUNT(*) FROM "
			+ "Mensagem m,"
			+ "PacienteLinhaCuidado plc "
			+ "WHERE m.linhaCuidado.id = plc.linhaCuidado.id "
			+ "AND plc.paciente.id= :idPaciente "			
			+ "AND NOT EXISTS ( "
			+ "SELECT 1 FROM "
			+ "Interacao i "
			+ "WHERE i.mensagem.id = m.id "
			+ "AND i.paciente.id = :idPaciente )")
	Integer showNumberNotReadMessageByPacienteLinhaCuidado(
			@Param("idPaciente") Integer idPaciente);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT COUNT(*) FROM "
			+ "Mensagem m "			
			+ "WHERE m.geral = true "						
			+ "AND NOT EXISTS ( "
			+ "SELECT 1 FROM "
			+ "Interacao i "
			+ "WHERE i.mensagem.id = m.id "
			+ "AND i.paciente.id = :idPaciente)")
	Integer showNumberNotReadMessageByPacienteGeral(@Param("idPaciente") Integer idPaciente);
	

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(*) FROM "
			+ "Mensagem m "			
			+ "WHERE m.paciente.id = :idPaciente "						
			+ "AND NOT EXISTS ( "
			+ "SELECT 1 FROM "
			+ "Interacao i "
			+ "WHERE i.mensagem.id = m.id)")
	Integer showNumberNotReadMessageByPacienteSpecificPaciente(
			@Param("idPaciente") Integer idPaciente);
}