package com.fiocruz.comunicacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fiocruz.comunicacao.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Transactional(readOnly=true)
	Pessoa findByEmail(String email);
	
	@Transactional(readOnly=true)
	Pessoa findByCpf(String cpf);
}