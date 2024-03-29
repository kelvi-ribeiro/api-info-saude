package com.info.saude.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date data;

	private String nome;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "local_exame_id")
	private LocalExame localExame;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	public Exame() {
		super();
	}

	public Exame(Integer id, Date data, String nome, String descricao, LocalExame localExame, Paciente paciente) {
		super();
		this.id = id;
		this.data = data;
		this.nome = nome;
		this.descricao = descricao;
		this.localExame = localExame;
		this.paciente = paciente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public LocalExame getLocalExame() {
		return localExame;
	}

	public void setLocalExame(LocalExame localExame) {
		this.localExame = localExame;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
