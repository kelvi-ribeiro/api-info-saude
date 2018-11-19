package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.Telefone;
import com.info.saude.dto.TelefoneDTO;
import com.info.saude.services.TelefoneService;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneResource {

	@Autowired
	private TelefoneService service;

	@RequestMapping(value = "/pessoa-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Telefone>> findByPessoaId(@PathVariable Integer id) {
		List<Telefone> pacienteLinhaCuidados = service.findByPessoaId(id);//
		return ResponseEntity.ok().body(pacienteLinhaCuidados);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Telefone> insert(@RequestBody TelefoneDTO objDto) {
		Telefone telefone = objDto.returnEntity();
		telefone = service.insertByPessoaId(telefone);
		return ResponseEntity.ok().body(service.insertByPessoaId(telefone));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Telefone obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}