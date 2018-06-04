package com.macuxi.camarao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macuxi.camarao.domain.Naturalidade;

@Repository
public interface NaturalidadeRepository extends JpaRepository<Naturalidade, Integer> {
	
}