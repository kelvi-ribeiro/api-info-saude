package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Telefone;
import com.info.saude.repositories.TelefoneRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repo;

	public Telefone find(Integer id) {
		Telefone obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Telefone.class.getName());
		}
		return obj;
	}

	public List<Telefone> findByPessoaId(Integer pessoaId) {
		List<Telefone> list = repo.findByPessoaId(pessoaId);
		return list;
	}

	public Telefone insertByPessoaId(Telefone telefone) {
		return repo.save(telefone);
	}
	
	public Telefone update(Telefone obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		if(find(id) != null) {
			repo.delete(id);
		}	
	}
}