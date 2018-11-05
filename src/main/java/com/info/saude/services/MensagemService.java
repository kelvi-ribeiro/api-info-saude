package com.info.saude.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Mensagem;
import com.info.saude.domain.Paciente;
import com.info.saude.dto.EmailTemplateDTO;
import com.info.saude.dto.MensagemDTO;
import com.info.saude.repositories.InteracaoRepository;
import com.info.saude.repositories.MensagemRepository;
import com.info.saude.repositories.PacienteRepository;
import com.info.saude.repositories.ProfissionalSaudeRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;
import com.info.saude.utils.AbstractEmailService;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository repo;

	@Autowired
	private InteracaoRepository interacaoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ProfissionalSaudeRepository profissionalSaudeRepository;

	@Autowired
	private AbstractEmailService<MensagemDTO> abstractEmailService;

	public Mensagem find(Integer id) {

		Mensagem obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Mensagem.class.getName());
		}
		return obj;
	}

	public Page<MensagemDTO> findAllPageable(Integer page, Integer linesPerPage) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.DESC, "id");
		final Page<Mensagem> pageMensagem = repo.findAll(pageRequest);
		final Page<MensagemDTO> mensagemDtoPage = pageMensagem.map(el -> {
			MensagemDTO mensagemDto = new MensagemDTO(el);
			mensagemDto.setNumberOfMessageRead(interacaoRepository.showNumberOfMessageRead(el.getId()));
			if (mensagemDto.getPacienteId() != null) {
				mensagemDto.setTotalPacienteMensagemEnviado(1);
			} else {
				mensagemDto.setTotalPacienteMensagemEnviado(pacienteRepository.showNumbersPacienteByLinhaCuidado(
						mensagemDto.getLinhaCuidadoId() != null ? mensagemDto.getLinhaCuidadoId() : 0));
			}
			return mensagemDto;
		});
		return mensagemDtoPage;

	}

	public Page<MensagemDTO> findAllByPacientePageable(Integer page, Integer linesPerPage, Integer idPaciente) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage);
		final Page<Mensagem> pageMensagem = repo.findAllByPaciente(idPaciente, pageRequest);
		final Page<MensagemDTO> mensagemDtoPage = pageMensagem.map(el -> {
			MensagemDTO mensagemDto = new MensagemDTO(el);
			if (interacaoRepository.findByPacienteIdAndMensagemId(idPaciente, mensagemDto.getId()) != null) {
				mensagemDto.setMensagemLida(true);
			}
			return mensagemDto;
		});
		return mensagemDtoPage;
	}

	public Integer showNumberNotReadMessageByPaciente(Integer idPaciente) {
		Integer numberNotMessageByPaciente = repo.showNumberNotReadMessageByPacienteLinhaCuidado(idPaciente);
		numberNotMessageByPaciente += repo.showNumberNotReadMessageByPacienteGeral(idPaciente);
		numberNotMessageByPaciente += repo.showNumberNotReadMessageByPacienteSpecificPaciente(idPaciente);
		return numberNotMessageByPaciente;
	}

	public Mensagem insert(Mensagem obj) {
		obj = repo.save(obj);
		obj.setProfissionalSaude(profissionalSaudeRepository.findOne(obj.getProfissionalSaude().getId()));
		MensagemDTO objDto = null;
		EmailTemplateDTO emailTemplateDto = null;
		if (obj.getPaciente() != null) {
			obj.setPaciente(pacienteRepository.findOne(obj.getPaciente().getId()));
			objDto = new MensagemDTO(obj);
			emailTemplateDto = new EmailTemplateDTO("Nova Mensagem", obj.getPaciente().getPessoa().getEmail(),
					"email/nova-mensagem", "mensagem");
			try {
				abstractEmailService.sendEmail(emailTemplateDto, objDto);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (obj.isGeral()) {
			List<Paciente> pacientes = pacienteRepository.findAll();
			for (Paciente paciente : pacientes) {
				emailTemplateDto = new EmailTemplateDTO("Nova Mensagem", paciente.getPessoa().getEmail(),
						"email/confirmacaoInscricao", "mensagem");
				try {
					abstractEmailService.sendEmail(emailTemplateDto, objDto);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return obj;
	}

	public Mensagem update(Mensagem obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.delete(id);
	}

}