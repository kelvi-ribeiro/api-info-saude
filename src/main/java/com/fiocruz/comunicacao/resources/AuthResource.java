package com.fiocruz.comunicacao.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiocruz.comunicacao.security.JWTUtil;
import com.fiocruz.comunicacao.security.UserSS;
import com.fiocruz.comunicacao.services.AuthService;
import com.fiocruz.comunicacao.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername()); 
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

//	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
//	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
//		service.sendNewPassword(objDto.getEmail());
//		return ResponseEntity.noContent().build();
//	}
}