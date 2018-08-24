package com.info.saude.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

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
//		return new MockEmailService();
//	}
}