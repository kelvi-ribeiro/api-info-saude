package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Endereco;
import com.info.saude.repositories.EnderecoRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	

	public Endereco find(Integer id) {
		Endereco obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName());
		}
		return obj;
	}

	public Endereco update(Endereco obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}