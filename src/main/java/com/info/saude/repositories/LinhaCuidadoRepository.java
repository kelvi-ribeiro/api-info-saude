package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.LinhaCuidado;

@Repository
public interface LinhaCuidadoRepository extends JpaRepository<LinhaCuidado, Integer> {
	
}