package com.info.saude.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.Medicamento;
import com.info.saude.domain.Paciente;
import com.info.saude.domain.TipoMedicamento;
import com.info.saude.utils.Utils;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String descricao;

	private int intervaloTempo;

	private String dataInicio;

	private String dataFim;

	private String horaInicial;

	private boolean ativo;

	private Integer pacienteId;

	private Integer tipoMedicamentoId;

	private String tipoMedicamentoNome;

	private String tipoMedicamentoCaminhoImagem;

	public MedicamentoDTO() {
		super();

	}

	public MedicamentoDTO(Medicamento medicamento) {
		super();
		this.id = medicamento.getId();
		this.nome = medicamento.getNome();
		this.descricao = medicamento.getDescricao();
		this.intervaloTempo = medicamento.getIntervaloTempo();
		this.dataInicio = medicamento.getDataInicio() != null ? Utils.dateToString(medicamento.getDataInicio()) : null;
		this.dataFim = medicamento.getDataFim() != null ? Utils.dateToString(medicamento.getDataFim()) : null;
		this.ativo = medicamento.isAtivo();
		this.horaInicial = medicamento.getHoraInicial() != null ? Utils.timeToString(medicamento.getHoraInicial())
				: null;
		this.pacienteId = medicamento.getPaciente().getId();
		if (medicamento.getTipoMedicamento() != null) {
			this.tipoMedicamentoId = medicamento.getTipoMedicamento().getId();
			this.tipoMedicamentoNome = medicamento.getTipoMedicamento().getNome();
			this.tipoMedicamentoCaminhoImagem = medicamento.getTipoMedicamento().getCaminhoImagem();

		}
	}

	public static List<MedicamentoDTO> returnListDto(List<Medicamento> list) {
		List<MedicamentoDTO> listDto = new ArrayList<MedicamentoDTO>();
		list.stream().forEach(x -> {
			listDto.add(new MedicamentoDTO(x));
		});
		return listDto;
	}

	public Medicamento returnEntity() {

		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		TipoMedicamento tipoMedicamento = new TipoMedicamento();
		tipoMedicamento.setId(this.tipoMedicamentoId);

		Medicamento medicamento = new Medicamento();
		medicamento.setId(this.id);
		medicamento.setNome(this.nome);
		medicamento.setDescricao(this.descricao);
		medicamento.setIntervaloTempo(this.intervaloTempo);
		medicamento.setDataInicio(Utils.sqlDateToDate(this.dataInicio));
		medicamento.setDataFim(Utils.sqlDateToDate(this.dataFim));
		medicamento.setHoraInicial(Utils.sqlDateToTime(this.horaInicial));
		medicamento.setPaciente(paciente);
		medicamento.setTipoMedicamento(tipoMedicamento);

		return medicamento;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIntervaloTempo() {
		return intervaloTempo;
	}

	public void setIntervaloTempo(int intervaloTempo) {
		this.intervaloTempo = intervaloTempo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Integer getTipoMedicamentoId() {
		return tipoMedicamentoId;
	}

	public void setTipoMedicamentoId(Integer tipoMedicamentoId) {
		this.tipoMedicamentoId = tipoMedicamentoId;
	}

	public String getTipoMedicamentoNome() {
		return tipoMedicamentoNome;
	}

	public void setTipoMedicamentoNome(String tipoMedicamentoNome) {
		this.tipoMedicamentoNome = tipoMedicamentoNome;
	}

	public String getTipoMedicamentoCaminhoImagem() {
		return tipoMedicamentoCaminhoImagem;
	}

	public void setTipoMedicamentoCaminhoImagem(String tipoMedicamentoCaminhoImagem) {
		this.tipoMedicamentoCaminhoImagem = tipoMedicamentoCaminhoImagem;
	}

}
