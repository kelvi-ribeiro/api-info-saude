package com.info.saude.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.LinhaCuidado;
import com.info.saude.domain.Paciente;
import com.info.saude.domain.PacienteLinhaCuidado;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteLinhaCuidadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer pacienteId;
	private String pacienteNome;

	private Integer linhaCuidadoId;
	private String linhaCuidadoNome;

	public PacienteLinhaCuidadoDTO() {
		super();

	}

	public PacienteLinhaCuidado returnEntity() {
		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		LinhaCuidado linhaCuidado = new LinhaCuidado();
		linhaCuidado.setId(this.linhaCuidadoId);

		PacienteLinhaCuidado pacienteLinhaCuidado = new PacienteLinhaCuidado();
		pacienteLinhaCuidado.setPaciente(paciente);
		pacienteLinhaCuidado.setLinhaCuidado(linhaCuidado);
		return pacienteLinhaCuidado;
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

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

	public String getPacienteNome() {
		return pacienteNome;
	}

	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}

	public Integer getLinhaCuidadoId() {
		return linhaCuidadoId;
	}

	public void setLinhaCuidadoId(Integer linhaCuidadoId) {
		this.linhaCuidadoId = linhaCuidadoId;
	}

	public String getLinhaCuidadoNome() {
		return linhaCuidadoNome;
	}

	public void setLinhaCuidadoNome(String linhaCuidadoNome) {
		this.linhaCuidadoNome = linhaCuidadoNome;
	}


}
