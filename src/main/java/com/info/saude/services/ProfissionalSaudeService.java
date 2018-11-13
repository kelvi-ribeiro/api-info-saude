package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.info.saude.domain.ProfissionalSaude;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.repositories.ProfissionalSaudeRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class ProfissionalSaudeService {
	
	@Autowired
	private ProfissionalSaudeRepository repo;
	
	@Autowired
	private PessoaRepository pessoaRepository;


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
	
	public ProfissionalSaude insert(ProfissionalSaude obj) {
		obj.getPessoa().setSenha("$2a$10$KfTG3aOA0VzZ8RQ8F1l7TuRO09r6Iv7O1d49/GRZ2axu0Y4jFEtiK");
		return repo.save(obj);

	}

	public ProfissionalSaude update(ProfissionalSaude obj) {
		obj.setPessoa(pessoaRepository.save(obj.getPessoa()));
		return repo.save(obj);
	}
	

}