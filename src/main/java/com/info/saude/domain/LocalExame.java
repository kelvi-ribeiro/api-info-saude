package com.info.saude.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "local_exame")
public class LocalExame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@OneToOne()
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@OneToMany(mappedBy = "localExame", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Exame> exames;

	public LocalExame() {
		super();
	}

	public LocalExame(Integer id, String nome, Endereco endereco, Paciente paciente, List<Exame> exames) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.paciente = paciente;
		this.exames = exames;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

}
