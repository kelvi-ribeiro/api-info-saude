package com.fiocruz.comunicacao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiocruz.comunicacao.domain.Endereco;
import com.fiocruz.comunicacao.domain.Naturalidade;
import com.fiocruz.comunicacao.domain.Pessoa;
import com.fiocruz.comunicacao.domain.Telefone;
import com.fiocruz.comunicacao.utils.Utils;



//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String cpf;

	private String rg;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	private String dataNascimento;

	private String dataInclusao;

	private String raca;

	private String sexo;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String senha;

	private int enderecoId;	

	private int naturalidadeId;

	private Set<Telefone> telefones = new HashSet<Telefone>();

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.cpf = pessoa.getCpf();
		this.nome = pessoa.getNome();
		this.dataNascimento = Utils.dateToString(pessoa.getDataNascimento());
		this.dataInclusao = Utils.dateTimeToString(pessoa.getDataInclusao());
		this.raca = pessoa.getRaca();
		this.sexo = pessoa.getSexo();
		this.email = pessoa.getEmail();
		this.sexo = pessoa.getSenha();
		this.enderecoId = pessoa.getEndereco() != null ? pessoa.getEndereco().getId() : null;		
		this.naturalidadeId = pessoa.getNaturalidade().getId() != null ? pessoa.getNaturalidade().getId() : null;
		this.telefones = pessoa.getTelefones();

	}

	public static List<PessoaDTO> returnListDto(List<Pessoa> list) {
		List<PessoaDTO> listDto = new ArrayList<PessoaDTO>();
		list.stream().forEach(x -> {
			listDto.add(new PessoaDTO(x));
		});
		return listDto;
	}

	public Pessoa returnEntity() {

		Naturalidade naturalidade = new Naturalidade();
		naturalidade.setId(this.naturalidadeId);	

		Endereco endereco = new Endereco();
		endereco.setId(this.enderecoId);

		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.id);
		pessoa.setCpf(this.cpf);
		pessoa.setRg(this.rg);
		pessoa.setNome(this.nome);
		pessoa.setDataNascimento(Utils.sqlDateToDate(this.dataNascimento));
		pessoa.setDataInclusao(Utils.sqlDateToDate(this.dataInclusao));
		pessoa.setRaca(this.raca);
		pessoa.setSexo(this.sexo);
		pessoa.setEmail(this.email);
		pessoa.setSenha(this.senha);		
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		return pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(int enderecoId) {
		this.enderecoId = enderecoId;
	}	

	public int getNaturalidadeId() {
		return naturalidadeId;
	}

	public void setNaturalidadeId(int naturalidadeId) {
		this.naturalidadeId = naturalidadeId;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

}
