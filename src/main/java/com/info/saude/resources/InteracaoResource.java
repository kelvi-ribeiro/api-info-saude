package com.info.saude.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.Interacao;
import com.info.saude.dto.InteracaoDTO;
import com.info.saude.services.InteracaoService;

@RestController
@RequestMapping(value="/interacoes")
public class InteracaoResource {
	
	@Autowired
	private InteracaoService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody InteracaoDTO objDto) {
		Interacao obj = service.insert(objDto);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/find-by-paciente-mensagem",method=RequestMethod.GET)
	public ResponseEntity<InteracaoDTO> findByPacienteIdAndMensagemId(
			@RequestParam(value = "idPaciente") Integer idPaciente,
			@RequestParam(value = "idMensagem") Integer idMensagem) {
		InteracaoDTO objDto = 
				new InteracaoDTO(
						service.findByPacienteIdAndMensagemId(idPaciente, idMensagem));
		return ResponseEntity.ok().body(objDto);
	}	
}