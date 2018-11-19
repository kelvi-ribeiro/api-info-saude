package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.TipoSanguineo;

@Repository
public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Integer> {
	
}