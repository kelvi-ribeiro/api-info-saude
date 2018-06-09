package com.fiocruz.comunicacao.services.validation;

//public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {
//	
//	@Autowired
//	private PessoaRepository repo;
//	@Override
//	public void initialize(UsuarioInsert ann) {
//	}
//
//	@Override
//	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
//
//		List<FieldMessage> list = new ArrayList<>();
//
//		if (!BR.isValidCPF(objDto.getCpf())) {
//			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
//		}		
//		
//		Paciente aux = repo.findByEmail(objDto.getEmail());
//		if(aux!=null) {
//			list.add(new FieldMessage("email","email Já existente"));
//		}
//
//		for (FieldMessage e : list) {
//			context.disableDefaultConstraintViolation();
//			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
//					.addConstraintViolation();
//		}
//		return list.isEmpty();
//	}
//}