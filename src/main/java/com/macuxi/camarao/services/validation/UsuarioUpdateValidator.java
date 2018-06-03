package com.macuxi.camarao.services.validation;

//public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {
//	@Autowired
//	private HttpServletRequest request;
//	@Autowired
//	private PessoaRepository repo;
//	@Override
//	public void initialize(UsuarioUpdate ann) {
//	}
//
//	@SuppressWarnings("unused")
//	@Override
//	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {
//		
//		@SuppressWarnings("unchecked")
//		Map<String,String> map =(Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		Integer uriId = Integer.parseInt(map.get("id"));
//		
//		List<FieldMessage> list = new ArrayList<>();
//
//		
//		
//		Paciente aux = repo.findByEmail(objDto.getEmail());
//		if(aux!=null && !aux.getId().equals(uriId)) {
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