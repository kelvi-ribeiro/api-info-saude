package com.info.saude.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "paciente_linha_cuidado",uniqueConstraints= {@UniqueConstraint
		(columnNames= {"paciente_id","linha_cuidado_id"},name="IDX_Paciente_Linha_Cuidado"
		)})

public class PacienteLinhaCuidado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore()
	@ManyToOne()
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "linha_cuidado_id")
	private LinhaCuidado linhaCuidado;

	public PacienteLinhaCuidado() {
		super();
	}

	public PacienteLinhaCuidado(Integer id, Paciente paciente, LinhaCuidado linhaCuidado) {		
		this.id = id;
		this.paciente = paciente;
		this.linhaCuidado = linhaCuidado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LinhaCuidado getLinhaCuidado() {
		return linhaCuidado;
	}

	public void setLinhaCuidado(LinhaCuidado linhaCuidado) {
		this.linhaCuidado = linhaCuidado;
	}

}