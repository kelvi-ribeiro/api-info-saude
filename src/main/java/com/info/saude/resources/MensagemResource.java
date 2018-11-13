package com.info.saude.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.Mensagem;
import com.info.saude.dto.MensagemDTO;
import com.info.saude.services.MensagemService;

@RestController
@RequestMapping(value = "/mensagens")
public class MensagemResource {

	@Autowired
	private MensagemService service;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<MensagemDTO>> findAllPageable(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage) {
		Page<MensagemDTO> objDto = service.findAllPageable(page, linesPerPage);
		return ResponseEntity.ok().body(objDto);
	}

	@RequestMapping(value = "/paciente/page", method = RequestMethod.GET)
	public ResponseEntity<Page<MensagemDTO>> findAllByPaciente(@RequestParam(value = "idPaciente") Integer idPaciente,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "60") Integer linesPerPage) {
		Page<MensagemDTO> objDtoPage = service.findAllByPacientePageable(page, linesPerPage, idPaciente);
		return ResponseEntity.ok().body(objDtoPage);
	}	

	@RequestMapping(value = "/page/any-field", method = RequestMethod.GET)
	public ResponseEntity<Page<MensagemDTO>> findByAnyField(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "campoPesquisa", defaultValue = "") String campoPesquisa,
			@RequestParam(value = "linhaCuidadoId", defaultValue = "0") Integer linhaCuidadoId) {
		Page<MensagemDTO> objDto = service.findByAnyField(page, linesPerPage, campoPesquisa, linhaCuidadoId);
		return ResponseEntity.ok().body(objDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody MensagemDTO objDto) throws InterruptedException {
		Mensagem obj = service.insert(objDto.returnEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody MensagemDTO objDto, @PathVariable Integer id) {
		Mensagem obj = objDto.returnEntity();
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/show-numbers-not-read-message", method = RequestMethod.GET)
	public ResponseEntity<Integer> showNumberNotReadMessageByPaciente(@RequestParam Integer idPaciente) {
		return ResponseEntity.ok().body(service.showNumberNotReadMessageByPaciente(idPaciente));
	}

}