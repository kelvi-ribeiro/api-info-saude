package com.fiocruz.comunicacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fiocruz.comunicacao.domain.Medicamento;
import com.fiocruz.comunicacao.dto.MedicamentoDTO;
import com.fiocruz.comunicacao.repositories.MedicamentoRepository;
import com.fiocruz.comunicacao.services.exceptions.DataIntegrityException;
import com.fiocruz.comunicacao.services.exceptions.ObjectNotFoundException;

@Service
public class MedicamentoService {

	
	@Autowired
	private MedicamentoRepository repo;


	public Medicamento find(Integer id) {

		Medicamento obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Medicamento.class.getName());
		}
		return obj;
	}
	
	public List<MedicamentoDTO> findAllAtivos(Integer idPaciente) {
		List<Medicamento> list = repo.findMedicamentosAtivosByPacienteId(idPaciente);
		List<MedicamentoDTO> listDto = MedicamentoDTO.returnListDto(list);
		return listDto;
	}
	
	public List<MedicamentoDTO> findAllInativos(Integer idPaciente) {
		List<Medicamento> list = repo.findMedicamentosInativoByPacienteId(idPaciente);
		List<MedicamentoDTO> listDto = MedicamentoDTO.returnListDto(list);
		return listDto;
	}



//	public Paciente insert(PacienteDTO obj) {
//		Paciente paciente = obj.returnEntity();
//		paciente = repo.save(paciente);		
//		return paciente;
//	}
//
//	public Pessoa update(Pessoa obj) {
//		Pessoa newObj = find(obj.getId());
//		updateData(newObj, obj);
//		return repo.save(newObj);
//	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	

}