package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.PacienteLinhaCuidado;
import com.info.saude.repositories.PacienteLinhaCuidadoRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteLinhaCuidadoService {

	@Autowired
	private PacienteLinhaCuidadoRepository repo;

	public PacienteLinhaCuidado find(Integer id) {
		PacienteLinhaCuidado obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + PacienteLinhaCuidado.class.getName());
		}
		return obj;
	}

	public List<PacienteLinhaCuidado> findByPacienteId(Integer pacienteId) {
		List<PacienteLinhaCuidado> list = repo.findByPacienteId(pacienteId);
		return list;
	}

	public PacienteLinhaCuidado insertByPacienteIdAndLinhaCuidadoId(PacienteLinhaCuidado pacienteLinhaCuidado) {
		return repo.save(pacienteLinhaCuidado);
	}
	
	public PacienteLinhaCuidado update(PacienteLinhaCuidado obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		if(find(id) != null) {
			repo.delete(id);
		}	
	}
}