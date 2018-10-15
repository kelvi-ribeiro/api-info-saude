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
	@JoinColumn(name = "tipo_sanguineo_id")
	private TipoSanguineo tipoSanguineo;

	@ManyToOne()
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne()
	@JoinColumn(name = "linha_cuidado_id")
	private LinhaCuidado linhaCuidado;

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Exame> exames = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<LocalExame> locaisExame = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<Medicamento> medicamentos = new ArrayList<>();

	public Paciente(Integer id, TipoSanguineo tipoSanguineo, Pessoa pessoa, LinhaCuidado linhaCuidado) {
		super();
		this.id = id;
		this.tipoSanguineo = tipoSanguineo;
		this.pessoa = pessoa;
		this.linhaCuidado = linhaCuidado;
	}

	public Paciente() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public LinhaCuidado getLinhaCuidado() {
		return linhaCuidado;
	}

	public void setLinhaCuidado(LinhaCuidado linhaCuidado) {
		this.linhaCuidado = linhaCuidado;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<LocalExame> getLocaisExame() {
		return locaisExame;
	}

	public void setLocaisExame(List<LocalExame> locaisExame) {
		this.locaisExame = locaisExame;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

}