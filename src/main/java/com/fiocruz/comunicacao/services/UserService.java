package com.fiocruz.comunicacao.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fiocruz.comunicacao.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
			
		}
	}
		
}
