package com.mybank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.config.JwtTokenUtil;
import com.mybank.dto.AuthToken;
import com.mybank.dto.AuthenticationRequest;
import com.mybank.entity.Users;
import com.mybank.exception.InvalidUserCredentials;
import com.mybank.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/generateToken")
	public ResponseEntity<Object> register(@RequestBody @Valid AuthenticationRequest users) {	
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getCustomerId(), users.getPassword()));
		 final Users user = userService.findOne(users.getCustomerId());
	        final String token = jwtTokenUtil.generateToken(user);
	       return new ResponseEntity<>(new AuthToken(token, user.getCustomerId()), HttpStatus.OK);
		}catch (AuthenticationException e) {
			throw new InvalidUserCredentials("Invaild Credentials");
			
		}
				
	    }
	
}