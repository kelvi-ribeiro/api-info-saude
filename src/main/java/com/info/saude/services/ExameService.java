package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Exame;
import com.info.saude.dto.ExameDTO;
import com.info.saude.repositories.ExameRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class ExameService {

	@Autowired
	private ExameRepository repo;

	public Exame find(Integer id) {
		Exame obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Exame.class.getName());
		}
		return obj;
	}

	public List<ExameDTO> findAllByPacienteId(Integer idPaciente) {
		List<Exame> list = repo.findAllByPacienteId(idPaciente);
		List<ExameDTO> listDto = ExameDTO.returnListDto(list);
		return listDto;
	}

	public Exame insert(ExameDTO obj) {
		Exame exame = obj.returnEntity();
		exame = repo.save(exame);
		return exame;
	}
	
	public Exame update(Exame obj) {		
		return repo.save(obj);
	}


	public void delete(Integer id) {
		find(id);
		repo.delete(id);
	}

}