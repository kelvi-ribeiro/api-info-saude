package com.info.saude.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int numero;

	private String logradouro;

	private String bairro;

	private String cep;

	private Double longitude;

	private Double latitude;

	@JsonIgnore
	@OneToOne(mappedBy = "endereco")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, int numero, String logradouro, String bairro, Double longitude, Double latitudade,
			String cep, Pessoa pessoa, Cidade cidade) {
		super();
		this.id = id;
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.longitude = longitude;
		this.latitude = latitudade;
		this.pessoa = pessoa;
		this.cidade = cidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
