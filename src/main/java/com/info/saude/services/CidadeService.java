package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Cidade;
import com.info.saude.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findAll() {
		List<Cidade> list = repo.findAll();
		return list;
	}

}