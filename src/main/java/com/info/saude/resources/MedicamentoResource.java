package com.info.saude.resources;

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

import com.info.saude.domain.Medicamento;
import com.info.saude.dto.MedicamentoDTO;
import com.info.saude.services.MedicamentoService;

@RestController
@RequestMapping(value="/medicamentos")
public class MedicamentoResource {
	
	@Autowired
	private MedicamentoService service;
	

	@RequestMapping(value="/ativos",method=RequestMethod.GET)
	public ResponseEntity<List<MedicamentoDTO>> findAllAtivos(@RequestParam(value="idPaciente") Integer idPaciente) {
		List<MedicamentoDTO> listDto = service.findAllAtivos(idPaciente);		 
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/inativos",method=RequestMethod.GET)
	public ResponseEntity<List<MedicamentoDTO>> findAllInativos(@RequestParam(value="idPaciente") Integer idPaciente) {
		List<MedicamentoDTO> listDto = service.findAllInativos(idPaciente);		 
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody MedicamentoDTO objDto) {
		Medicamento obj = service.insert(objDto);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody MedicamentoDTO objDto, @PathVariable Integer id) {
		Medicamento obj = objDto.returnEntity();
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="setAtivo/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> setAtivo(@PathVariable Integer id) {
		Medicamento obj = service.find(id);
		obj.setAtivo(true);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="setInativo/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> setInativo(@PathVariable Integer id) {
		Medicamento obj = service.find(id);
		obj.setAtivo(false);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}