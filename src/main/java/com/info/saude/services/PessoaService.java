package com.info.saude.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.info.saude.domain.Pessoa;
import com.info.saude.dto.NovaSenhaDTO;
import com.info.saude.dto.PessoaDTO;
import com.info.saude.repositories.EnderecoRepository;
import com.info.saude.repositories.PessoaRepository;
import com.info.saude.repositories.TelefoneRepository;
import com.info.saude.security.UserSS;
import com.info.saude.services.exceptions.AuthorizationException;
import com.info.saude.services.exceptions.DataIntegrityException;
import com.info.saude.services.exceptions.ObjectNotFoundException;
import com.info.saude.services.exceptions.SenhaIncorretaException;
import com.info.saude.utils.Utils;

@Service
public class PessoaService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private S3Service s3Service;

	public Pessoa find(Integer id) {

		Pessoa obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Pessoa insert(PessoaDTO obj) {
		obj.setSenha(pe.encode(obj.getSenha()));
		Pessoa pessoa = obj.returnEntity();
		try {
			pessoa = repo.save(pessoa);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Email já Cadastrado");
		}
		enderecoRepository.save(pessoa.getEndereco());
		telefoneRepository.save(pessoa.getTelefones());

		return pessoa;
	}

	public void alterarSenha(NovaSenhaDTO objNovaSenha) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		Pessoa pessoa = repo.findOne(user.getId());

		if (pe.matches(objNovaSenha.getSenhaAtual(), pessoa.getSenha())) {
			pessoa.setSenha(pe.encode(objNovaSenha.getNovaSenha()));
			repo.save(pessoa);
		} else {
			throw new SenhaIncorretaException("Senha Atual está incorreta: " + objNovaSenha.getSenhaAtual() + ", Tipo: "
					+ Pessoa.class.getName());

		}

	}

	public void setUserOnline(Integer pessoaId) {
		Pessoa pessoa = repo.findOne(pessoaId);
		pessoa.setUltimoAcesso(new Date());
		repo.save(pessoa);
	}

	public Pessoa findByEmail(String email) {
		Pessoa obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Nenhuma Pessoa Encontrada com esse email: " + email + ", Tipo: " + Pessoa.class.getName());
		}		
		return obj;
	}
	
	public Pessoa findByCpf(String cpf) {

		Pessoa obj = repo.findByCpf(cpf);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Nenhuma Pessoa Encontrada com esse cpf: " + cpf + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public URI uploadProfilePicture(MultipartFile multipartFile,Integer idPessoa) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		Pessoa pessoa = repo.findOne(idPessoa);
		String fileName = pessoa.getUrlFoto() != null ? pessoa.getUrlFoto()
				: "id=" + user.getId() + "&rand=" + Utils.newStringRandom() + ".jpg";
		pessoa.setUrlFoto(fileName);
		repo.save(pessoa);
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}