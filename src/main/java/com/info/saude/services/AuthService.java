package com.info.saude.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Pessoa;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String cpf) {
		
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);
		if (pessoa == null) {
			throw new ObjectNotFoundException("cpf não encontrado");
		}
		
		String newPass = newPassword();
		pessoa.setSenha(pe.encode(newPass));
		
		pessoaRepository.save(pessoa);
		/*emailService.sendNewPasswordEmail(cliente, newPass);*/
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}