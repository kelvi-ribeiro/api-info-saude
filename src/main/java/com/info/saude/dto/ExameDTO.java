package com.info.saude.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Exame;
import com.info.saude.domain.LocalExame;
import com.info.saude.domain.Paciente;
import com.info.saude.utils.Utils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String data;

	private String nome;

	private String descricao;

	private Integer localExameId;

	private String localExameNome;

	private String localExameCep;

	private Double localExameLatitude;

	private Double localExameLongitude;

	private Integer pacienteId;

	public ExameDTO() {
		super();

	}

	public ExameDTO(Exame exame) {
		super();
		this.id = exame.getId();
		this.data = exame.getData() != null ? Utils.dateToString(exame.getData()) : null;
		this.nome = exame.getNome();
		this.descricao = exame.getDescricao();
		this.localExameId = exame.getLocalExame().getId();
		this.localExameNome = exame.getLocalExame().getNome();
		this.localExameLatitude = (exame.getLocalExame() != null) && (exame.getLocalExame().getEndereco() != null)
				? exame.getLocalExame().getEndereco().getLatitude()
				: null;
		this.localExameLongitude = (exame.getLocalExame() != null) && (exame.getLocalExame().getEndereco() != null)
				? exame.getLocalExame().getEndereco().getLongitude()
				: null;
		this.localExameCep = (exame.getLocalExame() != null) && (exame.getLocalExame().getEndereco() != null)
				? exame.getLocalExame().getEndereco().getCep()
				: null;
		this.pacienteId = exame.getPaciente().getId();

	}

	public static List<ExameDTO> returnListDto(List<Exame> list) {
		List<ExameDTO> listDto = new ArrayList<ExameDTO>();
		list.stream().forEach(x -> {
			listDto.add(new ExameDTO(x));
		});
		return listDto;
	}

	public Exame returnEntity() {

		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		LocalExame localExame = new LocalExame();
		localExame.setId(this.localExameId);

		Exame exame = new Exame();
		if (this.data != null) {
			exame.setData(Utils.sqlDateTimeZoneToDate(this.data));
		}

		exame.setNome(this.nome);
		exame.setDescricao(this.descricao);
		exame.setPaciente(paciente);
		exame.setLocalExame(localExame);

		return exame;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getLocalExameId() {
		return localExameId;
	}

	public void setLocalExameId(Integer localExameId) {
		this.localExameId = localExameId;
	}

	public String getLocalExameNome() {
		return localExameNome;
	}

	public void setLocalExameNome(String localExameNome) {
		this.localExameNome = localExameNome;
	}

	public String getLocalExameCep() {
		return localExameCep;
	}

	public void setLocalExameCep(String localExameCep) {
		this.localExameCep = localExameCep;
	}

	public Double getLocalExameLatitude() {
		return localExameLatitude;
	}

	public void setLocalExameLatitude(Double localExameLatitude) {
		this.localExameLatitude = localExameLatitude;
	}

	public Double getLocalExameLongitude() {
		return localExameLongitude;
	}

	public void setLocalExameLongitude(Double localExameLongitude) {
		this.localExameLongitude = localExameLongitude;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

}
