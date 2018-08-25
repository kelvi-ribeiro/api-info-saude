package com.info.saude.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Pessoa;
import com.info.saude.domain.PessoaSenhaEsquecida;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.repositories.PessoaSenhaEsquecidaRepository;
import com.info.saude.services.email.EmailService;
import com.info.saude.services.exceptions.LinkSenhaEsquecidaUsado;

@Service
public class PessoaSenhaEsquecidaService {

	@Autowired
	private PessoaSenhaEsquecidaRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	EmailService emailService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService PessoaService;

	private Random rand = new Random();

	public PessoaSenhaEsquecida criarNovaSenha(PessoaSenhaEsquecida obj) {
		obj.setId(null);
		obj.setLink(this.newStringRandom());
		obj.setNovaSenha(pe.encode(obj.getNovaSenha()));
		obj.setLinkUsado(false);
		Pessoa pessoa = PessoaService.findByEmail(obj.getPessoa().getEmail());
		obj.setPessoa(pessoa);
		obj = repo.save(obj);
		emailService.sendOrderConfirmationEmail(obj);
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
			throw new LinkSenhaEsquecidaUsado(
					"<script>alert('Link Para alterar a senha já foi usado!!!')</script>");
		}
		return obj;
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}

	}

	private String newStringRandom() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
}