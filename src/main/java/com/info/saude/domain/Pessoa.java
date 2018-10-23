package com.info.saude.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.info.saude.domain.enums.Perfil;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Column(unique = true)
	private String cpf;

	@Column(name = "ultimo_acesso")
	private Date ultimoAcesso;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Column(name = "url_foto")
	private String urlFoto;

	@Column(name = "data_inclusao")
	private Date dataInclusao;

	private String sexo;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String senha;

	@OneToOne
	@JoinColumn(name = "endereco_id", unique = true)
	private Endereco endereco;

	@OneToMany(mappedBy = "pessoa", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<PessoaSenhaEsquecida> pessoas = new ArrayList<PessoaSenhaEsquecida>();

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

	public Pessoa(Integer id, String nome, String cpf, Date ultimoAcesso, Date dataNascimento, String urlFoto,
			Date dataInclusao, String sexo, String email, String senha, Endereco endereco,
			List<PessoaSenhaEsquecida> pessoas, Set<Telefone> telefones, Naturalidade naturalidade,
			Set<Integer> perfis) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.ultimoAcesso = ultimoAcesso;
		this.dataNascimento = dataNascimento;
		this.urlFoto = urlFoto;
		this.dataInclusao = dataInclusao;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.pessoas = pessoas;
		this.telefones = telefones;
		this.naturalidade = naturalidade;
		this.perfis = perfis;
		addPerfil(Perfil.USUARIO);
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public Date getDataInclusao() {
		return dataInclusao;
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

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

}