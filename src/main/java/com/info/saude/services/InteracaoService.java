package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Interacao;
import com.info.saude.dto.InteracaoDTO;
import com.info.saude.repositories.InteracaoRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class InteracaoService {

	@Autowired
	private InteracaoRepository repo;

//	public Medicamento find(Integer id) {
//
//		Medicamento obj = repo.findOne(id);
//		if (obj == null) {
//			throw new ObjectNotFoundException(
//					"Objeto não encontrado! Id: " + id + ", Tipo: " + Medicamento.class.getName());
//		}
//		return obj;
//	}
//
//	public List<MedicamentoDTO> findAllAtivos(Integer idPaciente) {
//		List<Medicamento> list = repo.findMedicamentosAtivosByPacienteId(idPaciente);
//		List<MedicamentoDTO> listDto = MedicamentoDTO.returnListDto(list);
//		return listDto;
//	}
//
//	public List<MedicamentoDTO> findAllInativos(Integer idPaciente) {
//		List<Medicamento> list = repo.findMedicamentosInativoByPacienteId(idPaciente);
//		List<MedicamentoDTO> listDto = MedicamentoDTO.returnListDto(list);
//		return listDto;
//	}

	public Interacao insert(InteracaoDTO objDto) {
		Interacao interacao = objDto.returnEntity();
		interacao = repo.save(interacao);
		return interacao;
	}

	public Interacao findByPacienteIdAndMensagemId(Integer idPaciente, Integer idMensagem) {
		Interacao obj = repo.findByPacienteIdAndMensagemId(idPaciente, idMensagem);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! IdPaciente: " + idPaciente + "idMensagem: "
					+ idMensagem + ", Tipo: " + Interacao.class.getName());
		}
		return obj;

	}

}