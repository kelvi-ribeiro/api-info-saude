package com.info.saude.services.email;

import org.springframework.mail.SimpleMailMessage;

import com.info.saude.domain.PessoaSenhaEsquecida;

public interface EmailService {

	void sendOrderConfirmationEmail(PessoaSenhaEsquecida obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void prepareSimpleMailMessagePessoaSenhaEsquecida(SimpleMailMessage msg);
	
	
}
