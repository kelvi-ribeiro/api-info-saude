package com.info.saude.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Cidade;
import com.info.saude.domain.Endereco;
import com.info.saude.domain.Naturalidade;
import com.info.saude.domain.Pessoa;
import com.info.saude.domain.Telefone;
import com.info.saude.domain.enums.Sexo;
import com.info.saude.utils.Utils;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String cpf;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	private String dataNascimento;

	private String dataInclusao;

	private Integer sexo;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String senha;

	private int naturalidadeId;

	// Endereco

	private int enderecoId;

	private int numeroEndereco;

	private String rua;

	private String bairro;

	private int cidadeId;

	private String nomeCidade;

	private String cep;

	// Telefone

	private Set<Telefone> telefones = new HashSet<Telefone>();

	private String numero1;

	private String numero2;

	private String numero3;

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.cpf = pessoa.getCpf();
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento() != null ? Utils.dateToString(pessoa.getDataNascimento())
				: null;
		this.dataInclusao = pessoa.getDataInclusao() != null ? Utils.dateTimeToString(pessoa.getDataInclusao()) : null;
		this.sexo = Sexo.descricaoToEnum((pessoa.getSexo())).getCod();
		this.email = pessoa.getEmail();
		this.senha = pessoa.getSenha();
		// Endereco
		this.enderecoId = pessoa.getEndereco() != null ? pessoa.getEndereco().getId() : null;
		this.bairro = pessoa.getEndereco() != null ? pessoa.getEndereco().getBairro() : null;
		this.cep = pessoa.getEndereco() != null ? pessoa.getEndereco().getCep() : null;
		this.nomeCidade = pessoa.getEndereco() != null && pessoa.getEndereco().getCidade() != null
				? pessoa.getEndereco().getCidade().getNome()
				: null;
		this.naturalidadeId = pessoa.getNaturalidade() != null ? pessoa.getNaturalidade().getId() : null;
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

		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.id);
		pessoa.setCpf(this.cpf);
		pessoa.setNome(this.nome);
		pessoa.setDataNascimento(Utils.brazilianDateToDate(this.dataNascimento));
		pessoa.setSexo(Sexo.toEnum(this.sexo).getDescricao());
		pessoa.setEmail(this.email);
		pessoa.setSenha(this.senha);
		pessoa.setNaturalidade(naturalidade);

		Cidade cidade = new Cidade();
		cidade.setId(this.cidadeId);

		Endereco endereco = new Endereco();
		endereco.setBairro(this.bairro);
		endereco.setCep(this.cep);
		endereco.setRua(this.rua);
		endereco.setNumero(this.numeroEndereco);
		endereco.setCidade(cidade);
		endereco.setPessoa(pessoa);

		pessoa.setEndereco(endereco);
		if (this.numero1 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setNumero(this.numero1);
			pessoa.getTelefones().add(telefone);
		}
		if (this.numero2 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setNumero(this.numero2);
			pessoa.getTelefones().add(telefone);
		}
		if (this.numero3 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setNumero(this.numero3);
			pessoa.getTelefones().add(telefone);
		}
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

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
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

	public int getNaturalidadeId() {
		return naturalidadeId;
	}

	public void setNaturalidadeId(int naturalidadeId) {
		this.naturalidadeId = naturalidadeId;
	}

	public int getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(int enderecoId) {
		this.enderecoId = enderecoId;
	}

	public int getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(int cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getNumero1() {
		return numero1;
	}

	public void setNumero1(String numero1) {
		this.numero1 = numero1;
	}

	public String getNumero2() {
		return numero2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

	public String getNumero3() {
		return numero3;
	}

	public void setNumero3(String numero3) {
		this.numero3 = numero3;
	}

}
