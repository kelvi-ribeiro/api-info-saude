package com.fiocruz.comunicacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fiocruz.comunicacao.domain.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
	
	@Transactional(readOnly=true)	
	List<Medicamento> findMedicamentosByPacienteId(Integer idPaciente);
}