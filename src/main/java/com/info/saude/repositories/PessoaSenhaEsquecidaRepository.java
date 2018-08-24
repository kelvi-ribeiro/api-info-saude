package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.PessoaSenhaEsquecida;

@Repository
public interface PessoaSenhaEsquecidaRepository extends JpaRepository<PessoaSenhaEsquecida, Integer> {
	
	PessoaSenhaEsquecida findByLink(@Param("link") String link);
	
}