package com.info.saude.services.email;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.mail.SimpleMailMessage;

import com.info.saude.domain.PessoaSenhaEsquecida;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Value("${base.url}")
	private String baseUrl;

	@Autowired
	private EmbeddedWebApplicationContext appContext;

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
				+ this.baseUrl + "/esqueceuSenha/" + obj.getLink());
		return sm;
	}

	public String getBaseUrl() throws UnknownHostException {
		Connector connector = ((TomcatEmbeddedServletContainer) appContext.getEmbeddedServletContainer()).getTomcat()
				.getConnector();
		String scheme = connector.getScheme();
		String ip = InetAddress.getLocalHost().getHostAddress();
		int port = connector.getPort();
		String contextPath = appContext.getServletContext().getContextPath();
		return scheme + "://" + ip + ":" + port + contextPath;
	}
}