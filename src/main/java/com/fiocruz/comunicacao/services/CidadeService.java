package com.fiocruz.comunicacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiocruz.comunicacao.domain.Cidade;
import com.fiocruz.comunicacao.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findAll() {
		List<Cidade> list = repo.findAll();
		return list;
	}

}