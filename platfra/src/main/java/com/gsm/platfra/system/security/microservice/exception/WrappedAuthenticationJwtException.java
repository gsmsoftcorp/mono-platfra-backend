package com.gsm.platfra.system.security.microservice.exception;

import org.springframework.security.core.AuthenticationException;

public class WrappedAuthenticationJwtException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public WrappedAuthenticationJwtException(String msg, Throwable t) {
		super(msg, t);
	}


}
