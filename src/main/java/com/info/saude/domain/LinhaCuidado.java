package com.info.saude.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "linha_cuidado")
public class LinhaCuidado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String descricao;

	@Column(name = "caminho_imagem")
	private String caminhoImagem;

	@JsonIgnore
	@OneToMany(mappedBy = "linhaCuidado", cascade = CascadeType.ALL)
	private List<PacienteLinhaCuidado> pacienteLinhaCuidados = new ArrayList<>();

	public LinhaCuidado() {
		super();
	}

	public LinhaCuidado(Integer id, String nome, String descricao, String caminhoImagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.caminhoImagem = caminhoImagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

}