package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.LinhaCuidado;
import com.info.saude.repositories.LinhaCuidadoRepository;

@Service
public class LinhaCuidadoService {

	@Autowired
	private LinhaCuidadoRepository repo;

	public List<LinhaCuidado> findAll() {
		List<LinhaCuidado> list = repo.findAll();
		return list;
	}

}