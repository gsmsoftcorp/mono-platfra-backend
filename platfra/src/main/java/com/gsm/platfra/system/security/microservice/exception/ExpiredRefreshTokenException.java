package com.gsm.platfra.system.security.microservice.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredRefreshTokenException extends AuthenticationException {

	private static final long serialVersionUID = 1L;


	public ExpiredRefreshTokenException(String msg) {
		super(msg);
	}
	
	
	public ExpiredRefreshTokenException(String msg, Throwable t) {
		super(msg, t);
	}


}
