package com.lcd.jwt.service;

import com.lcd.jwt.rq.LoginRequest;

public interface OwnerTokenService {
	
	public Object login (LoginRequest request);

}
