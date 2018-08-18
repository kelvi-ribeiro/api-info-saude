package com.fiocruz.comunicacao.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fiocruz.comunicacao.domain.Pessoa;
import com.fiocruz.comunicacao.dto.NovaSenhaDTO;
import com.fiocruz.comunicacao.dto.PessoaDTO;
import com.fiocruz.comunicacao.repositories.CidadeRepository;
import com.fiocruz.comunicacao.repositories.EnderecoRepository;
import com.fiocruz.comunicacao.repositories.NaturalidadeRepository;
import com.fiocruz.comunicacao.repositories.PessoaRepository;
import com.fiocruz.comunicacao.repositories.TelefoneRepository;
import com.fiocruz.comunicacao.security.UserSS;
import com.fiocruz.comunicacao.services.exceptions.AuthorizationException;
import com.fiocruz.comunicacao.services.exceptions.DataIntegrityException;
import com.fiocruz.comunicacao.services.exceptions.ObjectNotFoundException;
import com.fiocruz.comunicacao.services.exceptions.SenhaIncorretaException;

@Service
public class PessoaService {

	// @Value("${img.profile.size}")
	// private int size;
	//
	// @Value("${img.prefix.client.profile}")
	// private String prefix;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private NaturalidadeRepository naturalidadeRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private S3Service s3Service;

	private Random rand = new Random();

	public Pessoa find(Integer id) {

		// UserSS user = UserService.authenticated();
		// if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))
		// {
		// throw new AuthorizationException("Acesso negado");
		// }

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

	// public Pessoa update(Pessoa obj) {
	// Pessoa newObj = find(obj.getId());
	// updateData(newObj, obj);
	// return repo.save(newObj);
	// }

	// public void delete(Integer id) {
	// find(id);
	// try {
	// repo.delete(id);
	// } catch (DataIntegrityViolationException e) {
	// throw new DataIntegrityException("Não é possível excluir porque há pedidos
	// relacionados");
	// }
	// }

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Pessoa findByEmail(String email) {

		// UserSS user = UserService.authenticated();
		// if (user == null || !user.hasRole(Perfil.ADMIN) &&
		// !email.equals(user.getUsername())) {
		// throw new AuthorizationException("Acesso negado");
		// }

		Pessoa obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! email: " + email + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		Pessoa pessoa = repo.findOne(user.getId());
		String fileName = pessoa.getUrlFoto() != null ? pessoa.getUrlFoto()
				: "id=" + user.getId() + "&rand=" + this.newStringRandom() + ".jpg";
		pessoa.setUrlFoto(fileName);
		repo.save(pessoa);
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
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