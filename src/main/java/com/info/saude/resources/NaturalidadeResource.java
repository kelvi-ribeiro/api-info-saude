package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.Naturalidade;
import com.info.saude.services.NaturalidadeService;

@RestController
@RequestMapping(value = "/naturalidades")
public class NaturalidadeResource {

	@Autowired
	private NaturalidadeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Naturalidade>> findAll() {
		List<Naturalidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}