package com.fiocruz.comunicacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiocruz.comunicacao.domain.Paciente;
import com.fiocruz.comunicacao.domain.Pessoa;
import com.fiocruz.comunicacao.dto.PacienteDTO;
import com.fiocruz.comunicacao.repositories.PacienteRepository;
import com.fiocruz.comunicacao.repositories.PessoaRepository;
import com.fiocruz.comunicacao.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

//	@Value("${img.profile.size}")
//	private int size;
//
//	@Value("${img.prefix.client.profile}")
//	private String prefix;

	
	
	@Autowired
	private PacienteRepository repo;
	@Autowired
	private PessoaRepository repoPessoa;

	public Paciente find(Integer id) {
		
//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Paciente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Paciente insert(PacienteDTO obj) {
		Paciente paciente = obj.returnEntity();
		paciente = repo.save(paciente);		
		return paciente;
	}

//	public Pessoa update(Pessoa obj) {
//		Pessoa newObj = find(obj.getId());
//		updateData(newObj, obj);
//		return repo.save(newObj);
//	}

//	public void delete(Integer id) {
//		find(id);
//		try {
//			repo.delete(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
//		}
//	}

	public List<Paciente> findAll() {
		return repo.findAll();
	}

	public Paciente findByPessoaId(Integer idPessoa) {

//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Paciente obj = repo.findByPessoaId(idPessoa);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + idPessoa + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}
	
	public Paciente findByPessoaEmail(String email) {

//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Paciente obj = repo.findByPessoaEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Email: " + email + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}
	
	public Paciente findByPessoaCpf(String cpf) {

//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Paciente obj = repo.findByPessoaCpf(cpf);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + obj.getId() + ", Tipo: " + Paciente.class.getName());
		}
		return obj;
	}

//	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
//		return repo.findAll(pageRequest);
//	}
	

//	public Usuario fromDTO(UsuarioNewDTO objDto) {
//		Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpf(),
//				pe.encode(objDto.getSenha()));
//		Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
//		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
//				objDto.getBairro(), objDto.getCep(), cli, cid);
//		cli.getEnderecos().add(end);
//		cli.getTelefones().add(objDto.getTelefone1());
//		if (objDto.getTelefone2() != null) {
//			cli.getTelefones().add(objDto.getTelefone2());
//		}
//		if (objDto.getTelefone3() != null) {
//			cli.getTelefones().add(objDto.getTelefone3());
//		}
//		return cli;
//	}

	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

//	public URI uploadProfilePicture(MultipartFile multipartFile) {
//
//		UserSS user = UserService.authenticated();
//		if (user == null) {
//			throw new AuthorizationException("Acesso negado");
//		}
//
//		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
//		jpgImage = imageService.cropSquare(jpgImage);
//		jpgImage = imageService.resize(jpgImage, size);
//
//		String fileName = prefix + user.getId() + ".jpg";
//
//		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
//
//	}

}