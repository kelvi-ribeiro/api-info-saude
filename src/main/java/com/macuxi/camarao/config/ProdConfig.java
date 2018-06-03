package com.macuxi.camarao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

//	@Autowired
//	private DBService dbService;
//
//	@Bean
//	public boolean instantiateDatabase() throws Exception {
//		dbService.instantiateTestDatabase();
//		return true;
//	}
//	
//	@Bean
//	public EmailService emailService() {
//		return new SmtpEmailService();
//	}
}