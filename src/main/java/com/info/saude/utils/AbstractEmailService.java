package com.info.saude.utils;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.info.saude.dto.EmailTemplateDTO;

@Service
public class AbstractEmailService<T> {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	private MimeMessage prepareMimeMessage(EmailTemplateDTO emailTemplateDto, T object) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(emailTemplateDto.getTo());		
		mmh.setSubject(emailTemplateDto.getSubject());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplate(emailTemplateDto, object), true);

		return mimeMessage;
	}
	@Async
	public void sendEmail(EmailTemplateDTO emailTemplateDto, T object) throws InterruptedException, MessagingException {
		sendEmail(prepareMimeMessage(emailTemplateDto, object));
	}
	@Async
	private void sendEmail(MimeMessage mimeMessage) throws InterruptedException {
		Thread.sleep(10000);
		javaMailSender.send(mimeMessage);
	}

	protected String htmlFromTemplate(EmailTemplateDTO emailTemplateDto, T object) {
		Context context = new Context();
		context.setVariable(emailTemplateDto.getTemplateObjectName(), object);
		return templateEngine.process(emailTemplateDto.getTemplatePath(), context);
	}

}
