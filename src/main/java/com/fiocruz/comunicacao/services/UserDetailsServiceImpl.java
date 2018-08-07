package com.fiocruz.comunicacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiocruz.comunicacao.domain.Pessoa;
import com.fiocruz.comunicacao.repositories.PessoaRepository;
import com.fiocruz.comunicacao.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Pessoa pessoa = repo.findByCpf(cpf);
		if (pessoa == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new UserSS(pessoa.getId(), pessoa.getCpf(), pessoa.getSenha(), pessoa.getPerfis());
	}
}