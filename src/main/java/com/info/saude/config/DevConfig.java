package com.info.saude.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

//	@Bean
//	public boolean instantiateDatabase() throws Exception {
//
//		if (!"create".equals(strategy)) {
//			return false;
//		}
//
//		dbService.instantiateTestDatabase();
//
//		return true;
//	}

}
