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

	private String raca;

	private Integer sexo;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private String senha;

	private int naturalidadeId;

	// Endereco

	private int enderecoId;

	private int numeroEndereco;

	private String logradouro;

	private String bairro;

	private int cidadeId;

	private String nomeCidade;

	private String cep;

	// Telefone

	private Set<Telefone> telefones = new HashSet<Telefone>();

	private Integer codigoArea1;

	private String tipo1;

	private String numero1;

	private Integer codigoArea2;

	private String tipo2;

	private String numero2;

	private Integer codigoArea3;

	private String tipo3;

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
		this.raca = pessoa.getRaca();
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
		pessoa.setRaca(this.raca);
		pessoa.setSexo(Sexo.toEnum(this.sexo).getDescricao());
		pessoa.setEmail(this.email);
		pessoa.setSenha(this.senha);
		pessoa.setNaturalidade(naturalidade);

		Cidade cidade = new Cidade();
		cidade.setId(this.cidadeId);

		Endereco endereco = new Endereco();
		endereco.setBairro(this.bairro);
		endereco.setCep(this.cep);
		endereco.setLogradouro(this.logradouro);
		endereco.setNumero(this.numeroEndereco);
		endereco.setCidade(cidade);
		endereco.setPessoa(pessoa);

		pessoa.setEndereco(endereco);
		if (this.codigoArea1 != null && this.tipo1 != null && this.numero1 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setCodigoArea(this.codigoArea1);
			telefone.setNumero(this.numero1);
			telefone.setTipo(this.tipo1);
			pessoa.getTelefones().add(telefone);
		}
		if (this.codigoArea2 != null && this.tipo2 != null && this.numero2 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setCodigoArea(this.codigoArea2);
			telefone.setNumero(this.numero2);
			telefone.setTipo(this.tipo2);
			pessoa.getTelefones().add(telefone);
		}
		if (this.codigoArea3 != null && this.tipo3 != null && this.numero3 != null) {
			Telefone telefone = new Telefone();
			telefone.setPessoa(pessoa);
			telefone.setCodigoArea(this.codigoArea3);
			telefone.setNumero(this.numero3);
			telefone.setTipo(this.tipo3);
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

	public String getRaca() {
		return raca;
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
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

	public int getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
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

	public void setCodigoArea1(Integer codigoArea1) {
		this.codigoArea1 = codigoArea1;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	public void setNumero1(String numero1) {
		this.numero1 = numero1;
	}

	public void setCodigoArea2(Integer codigoArea2) {
		this.codigoArea2 = codigoArea2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

	public void setCodigoArea3(Integer codigoArea3) {
		this.codigoArea3 = codigoArea3;
	}

	public void setTipo3(String tipo3) {
		this.tipo3 = tipo3;
	}

	public void setNumero3(String numero3) {
		this.numero3 = numero3;
	}

}
