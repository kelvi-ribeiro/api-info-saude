package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Naturalidade;
import com.info.saude.repositories.NaturalidadeRepository;

@Service
public class NaturalidadeService {

	@Autowired
	private NaturalidadeRepository repo;

	public List<Naturalidade> findAll() {
		List<Naturalidade> list = repo.findAll();
		return list;
	}

}