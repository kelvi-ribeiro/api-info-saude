package com.info.saude.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.info.saude.domain.Pessoa;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.security.UserSS;

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