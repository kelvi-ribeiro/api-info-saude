package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.Naturalidade;

@Repository
public interface NaturalidadeRepository extends JpaRepository<Naturalidade, Integer> {
	
}