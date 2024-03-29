package com.info.saude.resources;

import java.net.URI;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.info.saude.domain.PessoaSenhaEsquecida;
import com.info.saude.dto.PessoaSenhaEsquecidaDTO;
import com.info.saude.services.PessoaSenhaEsquecidaService;

@RestController
@RequestMapping(value="/esqueceuSenha")
public class PessoaSenhaEsquecidaResource {
	
	@Autowired
	private PessoaSenhaEsquecidaService service;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> criarNovaSenha(@RequestBody PessoaSenhaEsquecidaDTO objDto) throws MessagingException, InterruptedException {
		PessoaSenhaEsquecida pessoaSenhaEsquecida = objDto.returnEntity();		
		 service.criarNovaSenha(pessoaSenhaEsquecida);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoaSenhaEsquecida.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{link}", method=RequestMethod.GET)
	public ResponseEntity<String> confirmarNovaSenha(@PathVariable String link) {	
		service.confirmarNovaSenha(link);		
		return ResponseEntity.ok().body("<script>alert('Senha Alterada Com sucesso!!!')</script>");
	}	
}