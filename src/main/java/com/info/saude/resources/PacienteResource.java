package com.info.saude.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.Paciente;
import com.info.saude.dto.PacienteDTO;
import com.info.saude.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PacienteDTO> find(@PathVariable Integer id) {
		Paciente paciente = service.find(id);
		PacienteDTO pacienteDto = new PacienteDTO(paciente);
		return ResponseEntity.ok().body(pacienteDto);
	}

//	@RequestMapping(value="/email", method=RequestMethod.GET)
//	public ResponseEntity<PessoaDTO> find(@RequestParam(value="value") String email) {
//		Pessoa pessoa = service.findByEmail(email);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
//		return ResponseEntity.ok().body(pessoaDto);
//	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PacienteDTO objDto) {
		Paciente obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/pessoa", method = RequestMethod.GET)
	public ResponseEntity<Paciente> findByPessoaId(@RequestParam(value = "idPessoa") Integer idPessoa) {
		Paciente paciente = service.findByPessoaId(idPessoa);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(paciente);
	}

	@RequestMapping(value = "/pessoaEmail", method = RequestMethod.GET)
	public ResponseEntity<Paciente> findByPessoaEmail(@RequestParam(value = "email") String email) {
		Paciente paciente = service.findByPessoaEmail(email);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(paciente);
	}

	@RequestMapping(value = "/pessoaCpf", method = RequestMethod.GET)
	public ResponseEntity<Paciente> findByPacienteCpf(@RequestParam(value = "cpf") String cpf) {
		Paciente paciente = service.findByPessoaCpf(cpf);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(paciente);
	}

//	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
//		Cliente obj = service.fromDTO(objDto);
//		obj.setId(id);
//		obj = service.update(obj);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PacienteDTO>> findAll() {
		List<Paciente> list = service.findAll();
		List<PacienteDTO> listDto = PacienteDTO.returnListDto(list);
		return ResponseEntity.ok().body(listDto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Paciente>> findByPessoaNomePage(
			@RequestParam(value = "campoPesquisa", defaultValue = "") String campoPesquisa,
			@RequestParam(value = "linhaCuidadoId", defaultValue = "") Integer linhaCuidadoId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "pessoa.nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Paciente> list = service.findByNamePage(page, linesPerPage, orderBy, direction, campoPesquisa,
				linhaCuidadoId);
		// Page<PacienteDTO> listDto = list.map(obj -> new PacienteDTO(obj));
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/number-online-users", method = RequestMethod.GET)
	public ResponseEntity<Integer> showNumberOnlineUsers() {		
		return ResponseEntity.ok().body(service.showNumberOnlineUsers());

	}
}