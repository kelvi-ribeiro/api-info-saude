package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Medicamento;
import com.info.saude.dto.MedicamentoDTO;
import com.info.saude.repositories.MedicamentoRepository;
import com.info.saude.services.exceptions.DataIntegrityException;
import com.info.saude.services.exceptions.ObjectNotFoundException;

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

	public Medicamento insert(MedicamentoDTO obj) {
		Medicamento medicamento = obj.returnEntity();
		medicamento = repo.save(medicamento);
		return medicamento;
	}

	public Medicamento update(Medicamento obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

}