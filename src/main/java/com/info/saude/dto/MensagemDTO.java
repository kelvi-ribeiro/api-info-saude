package com.info.saude.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.info.saude.domain.LinhaCuidado;
import com.info.saude.domain.Mensagem;
import com.info.saude.domain.Paciente;
import com.info.saude.domain.ProfissionalSaude;
import com.info.saude.utils.Utils;

//@UsuarioUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class MensagemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String assunto;

	private String mensagem;

	private String dataEnvio;

	private boolean geral;

	private Integer profissionalSaudeId;

	private String nomeProfissionalSaude;

	private Integer linhaCuidadoId;

	private String nomeLinhaCuidado;

	private Integer pacienteId;

	private String nomePaciente;

	public MensagemDTO() {
		super();

	}

	public MensagemDTO(Mensagem mensagem) {
		this.id = mensagem.getId();
		this.assunto = mensagem.getAssunto();
		this.mensagem = mensagem.getMensagem();
		this.dataEnvio = Utils.dateToString(mensagem.getDataEnvio());
		this.geral = mensagem.isGeral();
		this.profissionalSaudeId = mensagem.getProfissionalSaude() != null ? mensagem.getProfissionalSaude().getId()
				: null;
		this.nomeProfissionalSaude = (mensagem.getProfissionalSaude() != null
				&& mensagem.getProfissionalSaude().getPessoa() != null)
						? mensagem.getProfissionalSaude().getPessoa().getNome()
						: null;
		this.pacienteId = mensagem.getPaciente() != null ? mensagem.getPaciente().getId() : null;
		this.nomePaciente = (mensagem.getPaciente() != null && mensagem.getPaciente().getPessoa() != null)
				? mensagem.getPaciente().getPessoa().getNome()
				: null;
		this.linhaCuidadoId = mensagem.getLinhaCuidado() != null ? mensagem.getLinhaCuidado().getId() : null;
		this.nomeLinhaCuidado = mensagem.getLinhaCuidado() != null ? mensagem.getLinhaCuidado().getNome() : null;
	}

	public static List<MensagemDTO> returnListDto(List<Mensagem> list) {
		List<MensagemDTO> listDto = new ArrayList<MensagemDTO>();
		list.stream().forEach(x -> {
			listDto.add(new MensagemDTO(x));
		});
		return listDto;
	}

	public Mensagem returnEntity() {

		ProfissionalSaude profissionalSaude = new ProfissionalSaude();
		profissionalSaude.setId(this.profissionalSaudeId);

		Paciente paciente = new Paciente();
		paciente.setId(this.pacienteId);

		LinhaCuidado linhaCuidado = new LinhaCuidado();
		linhaCuidado.setId(this.linhaCuidadoId);

		Mensagem mensagem = new Mensagem();

		mensagem.setAssunto(this.assunto);
		mensagem.setMensagem(this.mensagem);
		mensagem.setGeral(this.geral);				
		mensagem.setProfissionalSaude(profissionalSaude);
		
		if (paciente.getId() != null) {
			mensagem.setPaciente(paciente);
		}
		if (linhaCuidado.getId() != null) {
			mensagem.setLinhaCuidado(linhaCuidado);
		}

		return mensagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public boolean isGeral() {
		return geral;
	}

	public void setGeral(boolean geral) {
		this.geral = geral;
	}

	public Integer getProfissionalSaudeId() {
		return profissionalSaudeId;
	}

	public void setProfissionalSaudeId(Integer profissionalSaudeId) {
		this.profissionalSaudeId = profissionalSaudeId;
	}

	public String getNomeProfissionalSaude() {
		return nomeProfissionalSaude;
	}

	public void setNomeProfissionalSaude(String nomeProfissionalSaude) {
		this.nomeProfissionalSaude = nomeProfissionalSaude;
	}

	public Integer getLinhaCuidadoId() {
		return linhaCuidadoId;
	}

	public void setLinhaCuidadoId(Integer linhaCuidadoId) {
		this.linhaCuidadoId = linhaCuidadoId;
	}

	public String getNomeLinhaCuidado() {
		return nomeLinhaCuidado;
	}

	public void setNomeLinhaCuidado(String nomeLinhaCuidado) {
		this.nomeLinhaCuidado = nomeLinhaCuidado;
	}

	public Integer getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Integer pacienteId) {
		this.pacienteId = pacienteId;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

}
