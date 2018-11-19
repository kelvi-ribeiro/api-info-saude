package com.info.saude.services;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Pessoa;
import com.info.saude.domain.PessoaSenhaEsquecida;
import com.info.saude.dto.EmailTemplateDTO;
import com.info.saude.dto.PessoaSenhaEsquecidaDTO;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.repositories.PessoaSenhaEsquecidaRepository;
import com.info.saude.services.exceptions.LinkSenhaEsquecidaUsado;
import com.info.saude.utils.AbstractEmailService;
import com.info.saude.utils.Utils;

@Service
public class PessoaSenhaEsquecidaService {

	@Autowired
	private PessoaSenhaEsquecidaRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	AbstractEmailService<PessoaSenhaEsquecidaDTO> abstractEmailService; 

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService PessoaService;	
	
	@Value("${base.url}")
	private String baseUrl;

	public PessoaSenhaEsquecida criarNovaSenha(PessoaSenhaEsquecida obj) throws MessagingException, InterruptedException {
		obj.setId(null);
		obj.setLink(Utils.newStringRandom());
		obj.setNovaSenha(pe.encode(obj.getNovaSenha()));
		obj.setLinkUsado(false);
		Pessoa pessoa = PessoaService.findByEmail(obj.getPessoa().getEmail());
		obj.setPessoa(pessoa);
		obj = repo.save(obj);
		StringBuffer emails = new StringBuffer();
		emails.append(obj.getPessoa().getEmail());
		EmailTemplateDTO emailTemplateDto = new EmailTemplateDTO("Link para alterar a senha", emails,
		"email/esqueceu-senha/esqueceu-senha", "pessoaSenhaEsquecida");
		PessoaSenhaEsquecidaDTO objDto = new PessoaSenhaEsquecidaDTO(obj);		
		objDto.setBaseUrl(this.baseUrl);
		abstractEmailService.sendEmail(emailTemplateDto, objDto);
		return obj;
	}

	public PessoaSenhaEsquecida confirmarNovaSenha(String link) {
		PessoaSenhaEsquecida pessoaSenhaEsquecida = this.findByLink(link);
		pessoaSenhaEsquecida.setLinkUsado(true);
		Pessoa pessoa = pessoaRepository.findOne(pessoaSenhaEsquecida.getPessoa().getId());
		pessoa.setSenha(pessoaSenhaEsquecida.getNovaSenha());
		repo.save(pessoaSenhaEsquecida);
		pessoaRepository.save(pessoa);
		return pessoaSenhaEsquecida;
	}

	public PessoaSenhaEsquecida findByLink(String link) {

		PessoaSenhaEsquecida obj = repo.findByLink(link);
		if (obj.isLinkUsado()) {
			throw new LinkSenhaEsquecidaUsado("<script>alert('Link Para alterar a senha já foi usado!!!')</script>");
		}
		return obj;
	}

}