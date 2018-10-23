package com.info.saude.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne()
	@JoinColumn(name = "tipo_sanguineo_id")
	private TipoSanguineo tipoSanguineo;

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Exame> exames = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<LocalExame> locaisExame = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Medicamento> medicamentos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<PacienteLinhaCuidado> pacienteLinhaCuidados = new ArrayList<>();

	public Paciente() {
		super();
	}

	public Paciente(Integer id, Pessoa pessoa, TipoSanguineo tipoSanguineo) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.tipoSanguineo = tipoSanguineo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

}