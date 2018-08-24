package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
	
}