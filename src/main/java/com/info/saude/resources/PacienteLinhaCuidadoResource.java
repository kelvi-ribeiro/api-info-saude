package com.info.saude.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<PacienteLinhaCuidado> insert(@RequestBody PacienteLinhaCuidadoDTO objDto) { 
		PacienteLinhaCuidado obj = service.insertByPacienteIdAndLinhaCuidadoId(objDto.returnEntity());
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}