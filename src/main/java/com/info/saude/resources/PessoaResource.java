package com.info.saude.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.Pessoa;
import com.info.saude.dto.NovaSenhaDTO;
import com.info.saude.dto.PessoaDTO;
import com.info.saude.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PessoaDTO> find(@PathVariable Integer id) {
		Pessoa pessoa = service.find(id);
		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(pessoaDto);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<PessoaDTO> findByEmail(@RequestParam(value = "value") String email) {
		Pessoa pessoa = service.findByEmail(email);
		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(pessoaDto);
	}
	
	@RequestMapping(value = "/cpf", method = RequestMethod.GET)
	public ResponseEntity<PessoaDTO> findByCpf(@RequestParam(value = "value") String cpf) {
		Pessoa pessoa = service.findByCpf(cpf);
		PessoaDTO pessoaDto = new PessoaDTO(pessoa);
		return ResponseEntity.ok().body(pessoaDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PessoaDTO objDto) {
		Pessoa obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file,@RequestParam(value = "idPessoa") Integer idPessoa) {
		URI uri = service.uploadProfilePicture(file,idPessoa);
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/delete-perfil/{idPessoa}/{idPerfil}", method = RequestMethod.DELETE)
	public void deletePerfil(@PathVariable Integer idPessoa,@PathVariable Integer idPerfil) {		
		service.deletePerfil(idPessoa, idPerfil);		
	}
	
	@RequestMapping(value = "/add-perfil/{idPessoa}/{idPerfil}", method = RequestMethod.POST)
	public void addPerfil(@PathVariable Integer idPessoa,@PathVariable Integer idPerfil) {		
		service.addPerfil(idPessoa, idPerfil);		
	}

	@RequestMapping(value = "/alterarSenha", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterarSenha(@RequestBody NovaSenhaDTO novaSenha) {
		service.alterarSenha(novaSenha);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/setUserOnline", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterarSenha(@RequestParam(value = "pessoaId") Integer pessoaId) {
		service.setUserOnline(pessoaId);
		return ResponseEntity.noContent().build();
	}

}