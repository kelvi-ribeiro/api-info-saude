package com.macuxi.camarao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.macuxi.camarao.domain.Pessoa;
import com.macuxi.camarao.domain.enums.Perfil;
import com.macuxi.camarao.dto.PessoaDTO;
import com.macuxi.camarao.repositories.CidadeRepository;
import com.macuxi.camarao.repositories.EnderecoRepository;
import com.macuxi.camarao.repositories.NacionalidadeRepository;
import com.macuxi.camarao.repositories.NaturalidadeRepository;
import com.macuxi.camarao.repositories.PessoaRepository;
import com.macuxi.camarao.security.UserSS;
import com.macuxi.camarao.services.exceptions.AuthorizationException;
import com.macuxi.camarao.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

//	@Value("${img.profile.size}")
//	private int size;
//
//	@Value("${img.prefix.client.profile}")
//	private String prefix;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private NacionalidadeRepository nacionalidadeRepository;
	
	@Autowired
	private NaturalidadeRepository naturalidadeRepository;  
	

	public Pessoa find(Integer id) {

//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Pessoa obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Pessoa insert(PessoaDTO obj) {
		Pessoa pessoa = obj.returnEntity();
		pessoa = repo.save(pessoa);
		enderecoRepository.save(pessoa.getEndereco());		
		return pessoa;
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

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Pessoa findByEmail(String email) {

//		UserSS user = UserService.authenticated();
//		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Pessoa obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + obj.getId() + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	

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