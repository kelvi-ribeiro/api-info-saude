package com.fiocruz.comunicacao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiocruz.comunicacao.domain.Cidade;
import com.fiocruz.comunicacao.domain.Endereco;
import com.fiocruz.comunicacao.domain.LocalExame;
import com.fiocruz.comunicacao.domain.Paciente;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalExameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Integer enderecoId;

	private int enderecoNumero;

	private String enderecoLogradouro;

	private String enderecoBairro;

	private String enderecoCep;

	private Integer cidadeId;

	private String cidadeNome;

	private Integer pacienteId;

	public LocalExameDTO() {
		super();

	}

	public LocalExameDTO(LocalExame localExame) {
		super();
		this.id = localExame.getId();
		this.nome = localExame.getNome();
		this.enderecoId = localExame.getEndereco().getId();
		this.enderecoNumero = localExame.getEndereco().getNumero();
		this.enderecoLogradouro = localExame.getEndereco().getLogradouro();
		this.enderecoCep = localExame.getEndereco().getCep();
		this.enderecoBairro = localExame.getEndereco().getBairro();
		this.cidadeId = localExame.getEndereco().getCidade().getId();
		this.cidadeNome = localExame.getEndereco().getCidade().getNome();
		this.pacienteId = localExame.getPaciente().getId();

	}

	public static List<LocalExameDTO> returnListDto(List<LocalExame> list) {
		List<LocalExameDTO> listDto = new ArrayList<LocalExameDTO>();
		list.stream().forEach(x -> {
			listDto.add(new LocalExameDTO(x));
		});
		return listDto;
	}

	public LocalExame returnEntity() {

		Cidade cidade = new Cidade();
		cidade.setId(this.cidadeId);

		Endereco endereco = new Endereco();
		endereco.setId(this.enderecoId);
		endereco.setBairro(this.enderecoBairro);
		endereco.setCep(this.enderecoCep);
		endereco.setNumero(this.enderecoNumero);
		endereco.setLogradouro(this.enderecoLogradouro);
		endereco.setCidade(cidade);

		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		LocalExame localExame = new LocalExame();
		localExame.setNome(this.nome);
		localExame.setEndereco(endereco);
		localExame.setPaciente(paciente);

		return localExame;
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

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

	public int getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(int enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	public String getEnderecoBairro() {
		return enderecoBairro;
	}

	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	public String getEnderecoCep() {
		return enderecoCep;
	}

	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getCidadeNome() {
		return cidadeNome;
	}

	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

}
