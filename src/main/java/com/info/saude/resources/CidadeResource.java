package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.Cidade;
import com.info.saude.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findAllAtivos() {
		List<Cidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}