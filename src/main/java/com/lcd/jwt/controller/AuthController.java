package com.lcd.jwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcd.jwt.rq.LoginRequest;
import com.lcd.jwt.rq.SignupRequest;
import com.lcd.jwt.service.OwnerTokenService;
import com.lcd.jwt.service.SignupService;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private OwnerTokenService ownerTokenService;
	
	@PostMapping("/token")
  public ResponseEntity<?> signin(@RequestBody @Valid LoginRequest request) throws Exception {

    return ResponseEntity.ok(ownerTokenService.login(request));
  };
  
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) throws Exception {

    return ResponseEntity.status(HttpStatus.CREATED).body(signupService.signup(request));
  };

}
