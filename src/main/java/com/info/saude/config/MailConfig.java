package com.info.saude.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration 
public class MailConfig {

	@Bean
	public JavaMailSenderImpl mailSender() {
	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	javaMailSender.setHost("smtp.gmail.com");
	javaMailSender.setPort(587);
	javaMailSender.setUsername("pesca.camarao.macuxi@gmail.com");
	javaMailSender.setPassword("262517kaka");

	Properties props = javaMailSender.getJavaMailProperties();
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.auth", "false");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "false");

	return javaMailSender;
	}
    
}