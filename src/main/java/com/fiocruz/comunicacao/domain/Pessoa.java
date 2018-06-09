package com.fiocruz.comunicacao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiocruz.comunicacao.domain.enums.Perfil;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String rg;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Column(name = "data_inclusao")
	private Date dataInclusao;

	private String raca;

	private String sexo;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String senha;

	@OneToOne(mappedBy = "pessoa")
	private Endereco endereco;

	@OneToMany(mappedBy = "pessoa", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private Set<Telefone> telefones = new HashSet<Telefone>();

	@ManyToOne
	@JoinColumn(name = "naturalidade_id")
	private Naturalidade naturalidade;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Pessoa() {
		this.dataInclusao = new Date(System.currentTimeMillis());
		addPerfil(Perfil.USUARIO);
	}

	public Pessoa(Integer id, String nome, String cpf, String rg, Date dataNascimento, String raca,
			String sexo, String email, String senha, Endereco endereco, Set<Telefone> telefones,
			Naturalidade naturalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.dataInclusao = new Date(System.currentTimeMillis());
		this.raca = raca;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.telefones = telefones;
		this.naturalidade = naturalidade;
		addPerfil(Perfil.USUARIO);
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}	

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Naturalidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Naturalidade naturalidade) {
		this.naturalidade = naturalidade;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

}