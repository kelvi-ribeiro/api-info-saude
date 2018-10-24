package com.info.saude.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.PacienteLinhaCuidado;
import com.info.saude.dto.PacienteLinhaCuidadoDTO;
import com.info.saude.services.PacienteLinhaCuidadoService;

@RestController
@RequestMapping(value = "/pacientes-linhas-cuidado")
public class PacienteLinhaCuidadoResource {

	@Autowired
	private PacienteLinhaCuidadoService service;

	@RequestMapping(value = "/paciente-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PacienteLinhaCuidado>> findByPacienteCpf(@PathVariable Integer id) {
		List<PacienteLinhaCuidado> pacienteLinhaCuidados = service.findByPacienteId(id);//		
		return ResponseEntity.ok().body(pacienteLinhaCuidados);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PacienteLinhaCuidadoDTO objDto) { 
		PacienteLinhaCuidado obj = service.insertByPacienteIdAndLinhaCuidadoId(objDto.returnEntity());		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}