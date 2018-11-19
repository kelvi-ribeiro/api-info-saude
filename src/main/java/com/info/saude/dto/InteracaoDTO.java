package com.info.saude.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Interacao;
import com.info.saude.domain.Mensagem;
import com.info.saude.domain.Paciente;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class InteracaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer pacienteId;

	private Integer mensagemId;

	public InteracaoDTO() {
		super();

	}

	public InteracaoDTO(Interacao interacao) {
		this.id = interacao.getId();
		this.pacienteId = interacao.getPaciente() != null ? interacao.getPaciente().getId() : null;
		this.mensagemId = interacao.getMensagem().getId();
	}

	public static List<InteracaoDTO> returnListDto(List<Interacao> list) {
		List<InteracaoDTO> listDto = new ArrayList<InteracaoDTO>();
		list.stream().forEach(x -> {
			listDto.add(new InteracaoDTO(x));
		});
		return listDto;
	}

	public Interacao returnEntity() {
		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		Mensagem mensagem = new Mensagem();
		mensagem.setId(this.mensagemId);

		Interacao intercao = new Interacao();
		intercao.setPaciente(paciente);
		intercao.setMensagem(mensagem);
		return intercao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Integer getMensagemId() {
		return mensagemId;
	}

	public void setMensagemId(Integer mensagemId) {
		this.mensagemId = mensagemId;
	}

}
