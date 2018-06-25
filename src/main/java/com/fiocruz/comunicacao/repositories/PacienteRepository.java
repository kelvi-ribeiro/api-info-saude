package com.fiocruz.comunicacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fiocruz.comunicacao.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Paciente obj WHERE obj.pessoa.email=:email")
	Paciente findByPessoaEmail(@Param("email") String email);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Paciente obj WHERE obj.pessoa.id=:idPessoa")
	Paciente findByPessoaId(@Param("idPessoa") Integer idPessoa);
}