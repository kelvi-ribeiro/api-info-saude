package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.info.saude.domain.LocalExame;
import com.info.saude.dto.LocalExameDTO;
import com.info.saude.repositories.EnderecoRepository;
import com.info.saude.repositories.LocalExameRepository;
import com.info.saude.services.exceptions.DataIntegrityException;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class LocalExameService {

	@Autowired
	private LocalExameRepository repo;

	@Autowired
	private EnderecoRepository endrecoRepo;

	public LocalExame find(Integer id) {

		LocalExame obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + LocalExame.class.getName());
		}
		return obj;
	}

	public List<LocalExameDTO> findAllByPacienteId(Integer idPaciente) {
		List<LocalExame> list = repo.findLocaisExameByPacienteId(idPaciente);
		List<LocalExameDTO> listDto = LocalExameDTO.returnListDto(list);
		return listDto;
	}

	public LocalExame insert(LocalExameDTO obj) {
		LocalExame localExame = obj.returnEntity();
		endrecoRepo.save(localExame.getEndereco());
		localExame = repo.save(localExame);
		return localExame;
	}

	public LocalExame update(LocalExame obj) {
		endrecoRepo.save(obj.getEndereco());
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