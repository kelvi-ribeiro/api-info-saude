package com.fiocruz.comunicacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiocruz.comunicacao.domain.Naturalidade;

@Repository
public interface NaturalidadeRepository extends JpaRepository<Naturalidade, Integer> {
	
}