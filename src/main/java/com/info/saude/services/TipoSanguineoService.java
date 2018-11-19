package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.TipoSanguineo;
import com.info.saude.repositories.TipoSanguineoRepository;

@Service
public class TipoSanguineoService {

	@Autowired
	private TipoSanguineoRepository repo;

	public List<TipoSanguineo> findAll() {
		List<TipoSanguineo> list = repo.findAll();
		return list;
	}

}