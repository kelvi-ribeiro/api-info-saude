package com.info.saude.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.saude.domain.ProfissionalSaude;
import com.info.saude.services.ProfissionalSaudeService;



@RestController
@RequestMapping(value="/profissionais-saude")
public class ProfissionalSaudeResource {
	
	@Autowired
	private ProfissionalSaudeService service;
	
	
//	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody PacienteDTO objDto) {
//		Paciente obj = service.insert(objDto);		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}	
	
	

	@RequestMapping(value="/pessoaCpf", method=RequestMethod.GET)
	public ResponseEntity<ProfissionalSaude> findByPacienteCpf(@RequestParam(value="cpf") String cpf) {
		ProfissionalSaude obj = service.findByPessoaCpf(cpf);
//		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(obj);
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
//	}//	
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProfissionalSaude>> findPage(
			@RequestParam(value="nomePessoa", defaultValue="") String nomePessoa, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="pessoa.nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<ProfissionalSaude> list = service.findByNamePage(page, linesPerPage, orderBy, direction,nomePessoa);
		//Page<PacienteDTO> listDto = list.map(obj -> new PacienteDTO(obj));  		
		return ResponseEntity.ok().body(list);
	}


}