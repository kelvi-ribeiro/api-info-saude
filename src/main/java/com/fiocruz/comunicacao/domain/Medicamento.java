package com.fiocruz.comunicacao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String descricao;

	@Column(name = "intervalo_tempo")
	private int intervaloTempo;

	@Column(name = "data_inicio")
	private Date dataInicio;

	@Column(name = "data_fim")
	private Date dataFim;

	@Column(name = "hora_inicial",columnDefinition="TIME",length=5)
	private Date horaInicial;

	private boolean ativo;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	public Medicamento() {
		super();
	}

	public Medicamento(Integer id, String nome, String descricao, int intervaloTempo, Date dataInicio, Date dataFim,
			Date horaInicial, boolean ativo, Paciente paciente) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.intervaloTempo = intervaloTempo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horaInicial = horaInicial;
		this.ativo = ativo;
		this.paciente = paciente;
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

	public int getIntervaloTempo() {
		return intervaloTempo;
	}

	public void setIntervaloTempo(int intervaloTempo) {
		this.intervaloTempo = intervaloTempo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
