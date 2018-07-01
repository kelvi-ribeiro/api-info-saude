package com.fiocruz.comunicacao.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fiocruz.comunicacao.domain.LocalExame;
import com.fiocruz.comunicacao.dto.LocalExameDTO;
import com.fiocruz.comunicacao.services.LocalExameService;

@RestController
@RequestMapping(value="/locaisExame")
public class LocalExameResource {
	
	@Autowired
	private LocalExameService service;	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody LocalExameDTO objDto) {
		LocalExame obj = service.insert(objDto);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LocalExameDTO>> findAll(@RequestParam(value="idPaciente") Integer idPaciente) {
		List<LocalExameDTO> listDto = service.findAllByPacienteId(idPaciente);		 
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody LocalExameDTO objDto, @PathVariable Integer id) {
		LocalExame obj = objDto.returnEntity();
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}