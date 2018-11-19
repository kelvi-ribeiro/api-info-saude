package com.info.saude.resources;

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

import com.info.saude.domain.ProfissionalSaude;
import com.info.saude.domain.enums.Perfil;
import com.info.saude.services.ProfissionalSaudeService;

@RestController
@RequestMapping(value = "/profissionais-saude")
public class ProfissionalSaudeResource {

	@Autowired
	private ProfissionalSaudeService service;

	@RequestMapping(value = "/pessoaCpf", method = RequestMethod.GET)
	public ResponseEntity<ProfissionalSaude> findByPacienteCpf(@RequestParam(value = "cpf") String cpf) {
		ProfissionalSaude obj = service.findByPessoaCpf(cpf);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProfissionalSaude> insert(@RequestBody ProfissionalSaude obj) {
		obj = service.insert(obj);
		obj.getPessoa().setSenha("$2a$10$KfTG3aOA0VzZ8RQ8F1l7TuRO09r6Iv7O1d49/GRZ2axu0Y4jFEtiK");
		obj.getPessoa().addPerfil(Perfil.GERENTE);	
		return ResponseEntity.ok().body(obj);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ProfissionalSaude obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProfissionalSaude>> findPage(
			@RequestParam(value = "nomePessoa", defaultValue = "") String nomePessoa,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "pessoa.nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ProfissionalSaude> list = service.findByNamePage(page, linesPerPage, orderBy, direction, nomePessoa);
		// Page<PacienteDTO> listDto = list.map(obj -> new PacienteDTO(obj));
		return ResponseEntity.ok().body(list);
	}

}