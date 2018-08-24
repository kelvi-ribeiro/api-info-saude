package com.info.saude.services.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.info.saude.domain.PessoaSenhaEsquecida;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(PessoaSenhaEsquecida obj) {
		SimpleMailMessage sm = prepareSimpleMailMessagePessoaSenhaEsquecida(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessagePessoaSenhaEsquecida(PessoaSenhaEsquecida obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getPessoa().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Link para alterar a senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Acesse esse Link para alterar sua senha, ignore se você não tem a mínima ideia do que é isso: "
				+ "http://59c5e0bf.ngrok.io/esqueceuSenha/" + obj.getLink());
		return sm;
	}

}
