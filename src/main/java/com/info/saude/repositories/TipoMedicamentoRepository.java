package com.info.saude.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.saude.domain.TipoMedicamento;

@Repository
public interface TipoMedicamentoRepository extends JpaRepository<TipoMedicamento, Integer> {
	
}