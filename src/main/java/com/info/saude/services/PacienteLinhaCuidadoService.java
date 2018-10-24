package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.PacienteLinhaCuidado;
import com.info.saude.repositories.PacienteLinhaCuidadoRepository;

@Service
public class PacienteLinhaCuidadoService {

	@Autowired
	private PacienteLinhaCuidadoRepository repo;

	public List<PacienteLinhaCuidado> findByPacienteId(Integer pacienteId ) {
		List<PacienteLinhaCuidado> list = repo.findByPacienteId(pacienteId);
		return list;
	}
	
	public PacienteLinhaCuidado insertByPacienteIdAndLinhaCuidadoId(PacienteLinhaCuidado pacienteLinhaCuidado) {
		return repo.save(pacienteLinhaCuidado);		
		
	}

}