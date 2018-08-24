package com.info.saude.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Pessoa;
import com.info.saude.domain.PessoaSenhaEsquecida;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaSenhaEsquecidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String novaSenha;
	private Integer pessoaId;

	public PessoaSenhaEsquecidaDTO() {
		super();

	}

	public PessoaSenhaEsquecida returnEntity() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.pessoaId);
		
		PessoaSenhaEsquecida pessoaSenhaEsquecida = new PessoaSenhaEsquecida();
		pessoaSenhaEsquecida.setNovaSenha(this.novaSenha);
		pessoaSenhaEsquecida.setPessoa(pessoa);		

		return pessoaSenhaEsquecida;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

}
