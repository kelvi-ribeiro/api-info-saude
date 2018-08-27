package com.info.saude.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_senha_esquecida")
public class PessoaSenhaEsquecida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nova_senha")
	private String novaSenha;

	@ManyToOne()
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	private String link;

	@Column(name = "link_usado")
	private boolean linkUsado;

	public PessoaSenhaEsquecida() {
		super();
	}

	public PessoaSenhaEsquecida(Integer id, String novaSenha, Pessoa pessoa, String link, boolean linkUsado) {
		super();
		this.id = id;
		this.novaSenha = novaSenha;
		this.pessoa = pessoa;
		this.link = link;
		this.linkUsado = linkUsado;
	}

	public Integer getId() {
		return id;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isLinkUsado() {
		return linkUsado;
	}

	public void setLinkUsado(boolean linkUsado) {
		this.linkUsado = linkUsado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
