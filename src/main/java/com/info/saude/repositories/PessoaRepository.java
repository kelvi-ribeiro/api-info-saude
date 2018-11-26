package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Transactional(readOnly=true)
	Pessoa findByEmail(String email);	
	
	@Transactional
	@Modifying
	@Query(value ="UPDATE Pessoa p\n " + 
			"SET p.ultimoAcesso = NOW()\n " + 
			"where p.id = :pessoaId")
	void setUserOnline(@Param("pessoaId")  Integer pessoaId);
	
	
	@Transactional(readOnly=true)
	Pessoa findByCpf(String cpf);	
	
	
}