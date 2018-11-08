package com.info.saude.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Pessoa;
import com.info.saude.domain.PessoaSenhaEsquecida;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaSenhaEsquecidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String novaSenha;
	private String pessoaEmail;
	private String pessoaNome;
	private String link;	
	private String baseUrl;

	public PessoaSenhaEsquecidaDTO() {
		super();
	}

	public PessoaSenhaEsquecidaDTO(PessoaSenhaEsquecida pessoaSenhaEsquecida) {
		this.novaSenha = pessoaSenhaEsquecida.getNovaSenha();
		this.pessoaEmail = pessoaSenhaEsquecida.getPessoa().getEmail();
		this.link = pessoaSenhaEsquecida.getLink();
		this.pessoaNome = pessoaSenhaEsquecida.getPessoa().getNome();
	}

	public PessoaSenhaEsquecida returnEntity() {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(this.pessoaEmail);

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

	public String getPessoaEmail() {
		return pessoaEmail;
	}

	public void setPessoaEmail(String pessoaEmail) {
		this.pessoaEmail = pessoaEmail;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
