package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Paciente;
import com.info.saude.domain.Pessoa;
import com.info.saude.dto.PacienteDTO;
import com.info.saude.repositories.PacienteRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public Paciente find(Integer id) {

		Paciente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Paciente insert(PacienteDTO obj) {
		Paciente paciente = obj.returnEntity();
		paciente = repo.save(paciente);
		return paciente;
	}

	public List<Paciente> findAll() {
		return repo.findAll();
	}

	public Paciente findByPessoaId(Integer idPessoa) {

		Paciente obj = repo.findByPessoaId(idPessoa);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + idPessoa + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}

	public Paciente findByPessoaEmail(String email) {

		Paciente obj = repo.findByPessoaEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Email: " + email + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}

	public Paciente findByPessoaCpf(String cpf) {

		Paciente obj = repo.findByPessoaCpf(cpf);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + cpf + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}
	
	public Integer showNumberOnlineUsers() {
		return repo.showNumberOnlineUsers();
	}
	
	
	public Page<Paciente> findByNamePage(
			Integer page, 
			Integer linesPerPage, 
			String orderBy, 
			String direction,
			String campoPesquisa,
			Integer linhaCuidadoId) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		if(linhaCuidadoId !=null) {			
			if(repo.findByPessoaNomeContainingAndLinhaCuidadoId(campoPesquisa,linhaCuidadoId,pageRequest).hasContent()) {
				return repo.findByPessoaNomeContainingAndLinhaCuidadoId(campoPesquisa,linhaCuidadoId,pageRequest);
			}else if(repo.findByPessoaEmailContainingAndLinhaCuidadoId(campoPesquisa,linhaCuidadoId,pageRequest).hasContent()) {
				return repo.findByPessoaEmailContainingAndLinhaCuidadoId(campoPesquisa,linhaCuidadoId,pageRequest);				
			}else {
				return repo.findByPessoaCpfContainingAndLinhaCuidadoId(campoPesquisa,linhaCuidadoId,pageRequest);
			}			
		}else {
			if(repo.findByPessoaNomeContaining(campoPesquisa,pageRequest).hasContent()) {
				return repo.findByPessoaNomeContaining(campoPesquisa,pageRequest);				
			} else if(repo.findByPessoaEmailContaining(campoPesquisa,pageRequest).hasContent()) {
				return repo.findByPessoaEmailContaining(campoPesquisa,pageRequest);
			}else {
				return repo.findByPessoaCpfContaining(campoPesquisa,pageRequest);
			}
		}
	}
	

}