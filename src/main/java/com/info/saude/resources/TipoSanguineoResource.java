package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.TipoSanguineo;
import com.info.saude.services.TipoSanguineoService;

@RestController
@RequestMapping(value = "/tipos-sanguineo")
public class TipoSanguineoResource {

	@Autowired
	private TipoSanguineoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipoSanguineo>> findAll() {
		List<TipoSanguineo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}