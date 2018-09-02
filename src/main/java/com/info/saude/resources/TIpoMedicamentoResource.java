package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.TipoMedicamento;
import com.info.saude.services.TipoMedicamentoService;


@RestController
@RequestMapping(value = "/tiposMedicamentos")
public class TIpoMedicamentoResource {

	@Autowired
	private TipoMedicamentoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipoMedicamento>> findAllAtivos() {
		List<TipoMedicamento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}