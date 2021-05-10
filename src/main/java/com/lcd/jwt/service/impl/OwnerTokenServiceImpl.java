package com.lcd.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lcd.jwt.rq.LoginRequest;
import com.lcd.jwt.service.OwnerTokenService;
import com.lcd.jwt.util.JwtUtils;

@Service
public class OwnerTokenServiceImpl implements OwnerTokenService {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Object login(LoginRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		return jwtUtils.generateJwtToken(authentication);
	}

}
