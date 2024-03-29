package com.info.saude.resources;

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

import com.info.saude.domain.Paciente;
import com.info.saude.dto.PacienteDTO;
import com.info.saude.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {

	@Autowired
	private PacienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Paciente> find(@PathVariable Integer id) {
		Paciente paciente = service.find(id);
		return ResponseEntity.ok().body(paciente);
	}
	@PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Paciente> insert(@RequestBody Paciente obj) {
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Paciente obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//	
	@PreAuthorize("hasAnyRole('ADMIN') or hasRole('GERENTE')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PacienteDTO>> findAll() {
		List<Paciente> list = service.findAll();
		List<PacienteDTO> listDto = PacienteDTO.returnListDto(list);
		return ResponseEntity.ok().body(listDto);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Paciente>> findPacinteByPessoaNomeOrPessoaEmailOrPessoaCPfOrLinhaCuidadoId(
			@RequestParam(value = "linhaCuidadoId", defaultValue = "0") Integer linhaCuidadoId,
			@RequestParam(value = "campoPesquisa", defaultValue = "") String campoPesquisa,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage) {
		Page<Paciente> list = service.findPacinteByPessoaNomeOrPessoaEmailOrPessoaCPfOrLinhaCuidadoId(page,
				linesPerPage, linhaCuidadoId, campoPesquisa);
		// Page<PacienteDTO> listDto = list.map(obj -> new PacienteDTO(obj));
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE')")
	@RequestMapping(value = "/number-online-users", method = RequestMethod.GET)
	public ResponseEntity<Integer> showNumberOnlineUsers() {
		return ResponseEntity.ok().body(service.showNumberOnlineUsers());
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('GERENTE')")
	@RequestMapping(value = "/numbers-paciente-by-linha-cuidado", method = RequestMethod.GET)
	public ResponseEntity<Integer> showNumbersPacienteByLinhaCuidado(
			@RequestParam(value = "linhaCuidadoId", defaultValue = "0") Integer linhaCuidadoId) {
		return ResponseEntity.ok().body(service.showNumbersPacienteByLinhaCuidado(linhaCuidadoId));
	}
}