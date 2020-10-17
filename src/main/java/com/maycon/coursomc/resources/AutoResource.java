package com.maycon.coursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maycon.coursomc.dto.EmailDTO;
import com.maycon.coursomc.security.JWTUtil;
import com.maycon.coursomc.security.UserSS;
import com.maycon.coursomc.services.AuthService;
import com.maycon.coursomc.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AutoResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
	UserSS user = UserService.authenticated();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgotten", method=RequestMethod.POST)
	public ResponseEntity<Void> forgotten(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPasswordEmail(objDto.getEmail());
	return ResponseEntity.noContent().build();
	}
}
