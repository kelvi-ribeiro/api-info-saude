package com.info.saude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.TipoMedicamento;
import com.info.saude.repositories.TipoMedicamentoRepository;

@Service
public class TipoMedicamentoService {

	@Autowired
	private TipoMedicamentoRepository repo;

	public List<TipoMedicamento> findAll() {
		List<TipoMedicamento> list = repo.findAll();
		return list;
	}

}