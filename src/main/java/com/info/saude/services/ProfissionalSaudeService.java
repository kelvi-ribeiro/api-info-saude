package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.info.saude.domain.ProfissionalSaude;
import com.info.saude.repositories.ProfissionalSaudeRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class ProfissionalSaudeService {
	
	@Autowired
	private ProfissionalSaudeRepository repo;
	
//
//	public Paciente insert(PacienteDTO obj) {
//		Paciente paciente = obj.returnEntity();
//		paciente = repo.save(paciente);
//		return paciente;
//	}	


	public ProfissionalSaude findByPessoaCpf(String cpf) {

		ProfissionalSaude obj = repo.findByPessoaCpf(cpf);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + cpf + ", Tipo: " + ProfissionalSaude.class.getName());
		}
		return obj;
	}
	
	
	public Page<ProfissionalSaude> findByNamePage(Integer page, Integer linesPerPage, String orderBy, String direction,String nomePessoa) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByPessoaNomeContaining(nomePessoa,pageRequest);
	}
	

}