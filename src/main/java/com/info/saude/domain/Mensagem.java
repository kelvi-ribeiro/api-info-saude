package com.info.saude.domain;

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
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String assunto;
	
	@Column(length=600)
	private String mensagem;

	private boolean geral;

	@Column(name = "data_envio")
	private Date dataEnvio;

	@ManyToOne()
	@JoinColumn(name = "linha_cuidado_id")
	private LinhaCuidado linhaCuidado;

	@ManyToOne()
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne()
	@JoinColumn(name = "profissional_saude_id")
	private ProfissionalSaude profissionalSaude;

	public Mensagem() {
		super();
		this.dataEnvio = new Date();
	}

	public Mensagem(Integer id, String assunto, String mensagem, boolean geral,
			LinhaCuidado linhaCuidado, Paciente paciente, ProfissionalSaude profissionalSaude) {
		this.id = id;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.geral = geral;
		this.dataEnvio = new Date();
		this.linhaCuidado = linhaCuidado;
		this.paciente = paciente;
		this.profissionalSaude = profissionalSaude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isGeral() {
		return geral;
	}

	public void setGeral(boolean geral) {
		this.geral = geral;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LinhaCuidado getLinhaCuidado() {
		return linhaCuidado;
	}

	public void setLinhaCuidado(LinhaCuidado linhaCuidado) {
		this.linhaCuidado = linhaCuidado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

}