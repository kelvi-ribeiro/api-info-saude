package com.info.saude.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.ProfissionalSaude;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Integer> {
	
	
	@Transactional(readOnly=true)	
	ProfissionalSaude findByPessoaCpf(@Param("cpf") String cpf);
	
	@Transactional(readOnly=true)
	Page<ProfissionalSaude> findByPessoaNomeContaining(@Param("nomePessoa") String nomePessoa,@Param("pageRequest") Pageable pageRequest);
}