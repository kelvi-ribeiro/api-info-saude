package com.fiocruz.comunicacao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiocruz.comunicacao.domain.Medicamento;
import com.fiocruz.comunicacao.domain.Paciente;
import com.fiocruz.comunicacao.utils.Utils;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
//	private String data;
//
//	private String nome;
//
//	private int numeroEndereco;
//	
//	private String logradouro;
//
//	private String bairroEndereco;
//	
//	private String cep;
//
//	private Integer cidadeId;	
//
//	
//
//	public ExameDTO() {		
//		super();
//		
//	}
//
//	public ExameDTO(Medicamento medicamento) {
//		super();
//		this.id = medicamento.getId();
//		this.nome = medicamento.getNome();
//		this.descricao = medicamento.getDescricao();
//		this.intervaloTempo = medicamento.getIntervaloTempo();
//		this.dataInicio = medicamento.getDataInicio() != null ? Utils.dateToString(medicamento.getDataInicio()) : null;
//		this.dataFim = medicamento.getDataFim() != null ? Utils.dateToString(medicamento.getDataFim()) : null;
//		this.horaInicial = medicamento.getHoraInicial() != null ? Utils.timeToString(medicamento.getHoraInicial())
//				: null;
//		this.pacienteId = medicamento.getPaciente().getId();
//		this.ativo = medicamento.isAtivo();
//
//	}
//
//	public static List<ExameDTO> returnListDto(List<Medicamento> list) {
//		List<ExameDTO> listDto = new ArrayList<ExameDTO>();
//		list.stream().forEach(x -> {
//			listDto.add(new ExameDTO(x));
//		});
//		return listDto;
//	}
//
//	public Medicamento returnEntity() {
//
//		Paciente paciente = new Paciente();
//		paciente.setId(this.pacienteId);
//
//		Medicamento medicamento = new Medicamento();
//		medicamento.setId(this.id);
//		medicamento.setNome(this.nome);
//		medicamento.setDescricao(this.descricao);
//		medicamento.setIntervaloTempo(this.intervaloTempo);
//		medicamento.setDataInicio(Utils.sqlDateToDate(this.dataInicio));
//		medicamento.setDataFim(Utils.sqlDateToDate(this.dataFim));
//		medicamento.setHoraInicial(Utils.sqlDateToTime(this.horaInicial));		
//		medicamento.setPaciente(paciente);
//
//		return medicamento;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public int getIntervaloTempo() {
//		return intervaloTempo;
//	}
//
//	public void setIntervaloTempo(int intervaloTempo) {
//		this.intervaloTempo = intervaloTempo;
//	}
//
//	public String getDataInicio() {
//		return dataInicio;
//	}
//
//	public void setDataInicio(String dataInicio) {
//		this.dataInicio = dataInicio;
//	}
//
//	public String getDataFim() {
//		return dataFim;
//	}
//
//	public void setDataFim(String dataFim) {
//		this.dataFim = dataFim;
//	}
//
//	public String getHoraInicial() {
//		return horaInicial;
//	}
//
//	public void setHoraInicial(String horaInicial) {
//		this.horaInicial = horaInicial;
//	}
//
//	public boolean isAtivo() {
//		return ativo;
//	}
//
//	public void setAtivo(boolean ativo) {
//		this.ativo = ativo;
//	}
//
//	public Integer getPacienteId() {
//		return pacienteId;
//	}
//
//	public void setPacienteId(Integer pacienteId) {
//		this.pacienteId = pacienteId;
//	}

}
