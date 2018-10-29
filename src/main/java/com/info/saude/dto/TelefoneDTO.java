package com.info.saude.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Pessoa;
import com.info.saude.domain.Telefone;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelefoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer pessoaId;

	private String numero;

	public TelefoneDTO() {
		super();

	}

	public Telefone returnEntity() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.pessoaId);
		
		Telefone telefone = new Telefone();
		telefone.setNumero(this.numero);
		telefone.setPessoa(pessoa);
		
		return telefone;
	}
	
//	public static List<PacienteLinhaCuidadoDTO> returnListDto(List<PacienteLinhaCuidado> list) {
//	List<PacienteLinhaCuidadoDTO> listDto = new ArrayList<PacienteLinhaCuidadoDTO>();
//	list.stream().forEach(x -> {
//		listDto.add(new PacienteLinhaCuidadoDTO(x));
//	});
//	return listDto;
//}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


}
