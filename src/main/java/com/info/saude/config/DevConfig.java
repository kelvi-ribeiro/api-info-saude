package com.info.saude.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.info.saude.services.email.EmailService;
import com.info.saude.services.email.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
