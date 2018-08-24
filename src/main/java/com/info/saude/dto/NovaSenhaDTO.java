package com.info.saude.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class NovaSenhaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String novaSenha;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String senhaAtual;

	public NovaSenhaDTO() {
		super();
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

}