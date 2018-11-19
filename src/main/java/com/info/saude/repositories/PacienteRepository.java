package com.info.saude.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Paciente obj WHERE obj.pessoa.email=:email")
	Paciente findByPessoaEmail(@Param("email") String email);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Paciente obj WHERE obj.pessoa.cpf=:cpf")
	Paciente findByPessoaCpf(@Param("cpf") String cpf);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Paciente obj WHERE obj.pessoa.id=:idPessoa")
	Paciente findByPessoaId(@Param("idPessoa") Integer idPessoa);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT pa FROM Paciente  pa,Pessoa pe, PacienteLinhaCuidado plc\n"
			+ "WHERE pa.id = plc.paciente.id \n" + 
			"AND  pa.pessoa.id = pe.id "
			+ " AND plc.linhaCuidado.id = :linhaCuidadoId")
	Page<Paciente> findByAllByLinhaCuidadoId(
			@Param("linhaCuidadoId") Integer linhaCuidadoId,
			@Param("pageRequest") Pageable pageRequest
			);


//	@Transactional(readOnly = true)
//	@Query("SELECT count(*) FROM Paciente pa, Pessoa pe "
//			+ "where pa.pessoa.id=pe.id "   
//			 +"AND pa.pessoa.ultimoAcesso"   
//			 + "BETWEEN NOW() - INTERVAL \'80\' second  AND NOW()")	
//	Integer showNumberOnlineUsers();

	@Transactional(readOnly = true)
	@Query(value = "SELECT count(*)\n" + "FROM paciente\n" + "   ,pessoa\n" + "WHERE paciente.pessoa_id = pessoa.id\n"
			+ "AND pessoa.ultimo_acesso between NOW() - INTERVAL '80' second AND NOW()", nativeQuery = true)
	Integer showNumberOnlineUsers();

	

	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT pa FROM Paciente  pa,Pessoa pe, PacienteLinhaCuidado plc\n"
			+ "WHERE pa.id = plc.paciente.id \n" + "	AND  pa.pessoa.id = pe.id"
			+ " AND (plc.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) "
			+ " AND pe.nome LIKE CONCAT('%',:campoPesquisa,'%')" + " ORDER by pa.pessoa.nome")
	Page<Paciente> findPacienteByPessoaNomeOrLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT pa FROM Paciente  pa,Pessoa pe, PacienteLinhaCuidado plc\n"
			+ "WHERE pa.id = plc.paciente.id \n" + "	AND  pa.pessoa.id = pe.id"
			+ " AND (plc.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) "
			+ " AND pe.email LIKE CONCAT('%',:campoPesquisa,'%')" + " ORDER by pa.pessoa.nome")
	Page<Paciente> findPacienteByPessoaEmailOrLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT pa FROM Paciente  pa,Pessoa pe, PacienteLinhaCuidado plc\n"
			+ "WHERE pa.id = plc.paciente.id \n" + "	AND  pa.pessoa.id = pe.id"
			+ " AND (plc.linhaCuidado.id = :linhaCuidadoId  or :linhaCuidadoId = 0) "
			+ " AND pe.cpf LIKE CONCAT('%',:campoPesquisa,'%')" + " ORDER by pa.pessoa.nome")
	Page<Paciente> findPacienteByPessoaCpflOrLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(*) " + "FROM Paciente p, " + "PacienteLinhaCuidado plc "
			+ "WHERE p.id = plc.paciente.id " + "AND (plc.linhaCuidado.id = :linhaCuidadoId or :linhaCuidadoId = 0)")
	Integer showNumbersPacienteByLinhaCuidado(@Param("linhaCuidadoId") Integer linhaCuidadoId);

//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaNomeContainingAndLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
//			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);
//
//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaEmailContainingAndLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
//			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);
//
//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaCpfContainingAndLinhaCuidadoId(@Param("campoPesquisa") String campoPesquisa,
//			@Param("linhaCuidadoId") Integer linhaCuidadoId, @Param("pageRequest") Pageable pageRequest);
//
//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaNomeContaining(@Param("campoPesquisa") String campoPesquisa,
//			@Param("pageRequest") Pageable pageRequest);
//
//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaEmailContaining(@Param("campoPesquisa") String campoPesquisa,
//			@Param("pageRequest") Pageable pageRequest);
//
//	@Transactional(readOnly = true)
//	Page<Paciente> findByPessoaCpfContaining(@Param("campoPesquisa") String campoPesquisa,
//			@Param("pageRequest") Pageable pageRequest);
}