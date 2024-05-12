package com.gsm.platfra.system.security.microservice.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationTokentNotFoundException extends AuthenticationException{

	private static final long serialVersionUID = 1L;


	public AuthorizationTokentNotFoundException(String msg) {
		super(msg);
	}
	
	
	public AuthorizationTokentNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}


}
