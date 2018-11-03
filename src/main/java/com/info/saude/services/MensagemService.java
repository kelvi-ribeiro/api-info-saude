package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Mensagem;
import com.info.saude.repositories.MensagemRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository repo;

	public Mensagem find(Integer id) {

		Mensagem obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Mensagem.class.getName());
		}
		return obj;
	}

	public Page<Mensagem> findAllPageable(Integer page, Integer linesPerPage) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.ASC,"id");
		return repo.findAll(pageRequest);

	}
	
	public Page<Mensagem> findAllByPacientePageable(Integer page, Integer linesPerPage,Integer idPaciente) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.ASC,"id");
		return repo.findAllByPaciente(idPaciente, pageRequest);

	}

	public Mensagem insert(Mensagem obj) {
		obj = repo.save(obj);
		return obj;
	}

	public Mensagem update(Mensagem obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.delete(id);
	}

}