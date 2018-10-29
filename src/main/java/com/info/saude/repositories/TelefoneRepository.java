package com.info.saude.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
	@Transactional(readOnly = true)	
	List<Telefone> findByPessoaId(@Param("pessoaId") Integer pessoaId);
}