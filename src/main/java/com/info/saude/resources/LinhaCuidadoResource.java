package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.LinhaCuidado;
import com.info.saude.services.LinhaCuidadoService;

@RestController
@RequestMapping(value = "/linhas-cuidado")
public class LinhaCuidadoResource {

	@Autowired
	private LinhaCuidadoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LinhaCuidado>> findAllAtivos() {
		List<LinhaCuidado> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}