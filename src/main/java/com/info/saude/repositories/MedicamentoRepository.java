package com.info.saude.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.info.saude.domain.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Medicamento obj WHERE obj.paciente.id = :idPaciente and obj.ativo = 1")
	List<Medicamento> findMedicamentosAtivosByPacienteId(@Param("idPaciente") Integer idPaciente);
	
	@Transactional(readOnly=true)	
	@Query("SELECT obj FROM Medicamento obj WHERE obj.paciente.id = :idPaciente and obj.ativo = 0")
	List<Medicamento> findMedicamentosInativoByPacienteId(@Param("idPaciente") Integer idPaciente);
}