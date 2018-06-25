package com.fiocruz.comunicacao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiocruz.comunicacao.domain.Paciente;
import com.fiocruz.comunicacao.domain.Pessoa;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer pessoaId;

	public PacienteDTO() {
		super();
	}

	public PacienteDTO(Paciente paciente) {
		super();
		this.id = paciente.getId();
		this.pessoaId = paciente.getPessoa().getId();
		
	
	}

	public static List<PacienteDTO> returnListDto(List<Paciente> list) {
		List<PacienteDTO> listDto = new ArrayList<PacienteDTO>();
		list.stream().forEach(x -> {
			listDto.add(new PacienteDTO(x));
		});
		return listDto;
	}

	public Paciente returnEntity() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.pessoaId);
		
		Paciente paciente = new Paciente();
		paciente.setId(this.id);
		paciente.setPessoa(pessoa);
		
		return paciente;
	}

	

}
