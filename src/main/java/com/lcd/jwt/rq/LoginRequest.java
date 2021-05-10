package com.lcd.jwt.rq;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class LoginRequest {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
