/*
 * Copyright (C) Hanwha Systems Ltd., 2021. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha Systems Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha Systems Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */
package com.gsm.platfra.system.security.microservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsm.platfra.system.security.microservice.exception.AuthorizationTokentNotFoundException;
import com.gsm.platfra.system.security.microservice.exception.ExpiredRefreshTokenException;
import com.gsm.platfra.system.security.microservice.exception.WrappedAuthenticationJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @see SecurityConfig
 */
@Component
public class ServiceAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private Logger log = LoggerFactory.getLogger(ServiceAuthenticationFailureHandler.class);
	private final ObjectMapper objectMapper;

	ServiceAuthenticationFailureHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		// 400
		log.error("[ServiceAuthenticationFailureHandler] : Authentication failed.");

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		String message = "인증 실패";
		
		if(exception instanceof WrappedAuthenticationJwtException) {
			message = this.resolveMessage(exception.getCause());
		} else {
			message = this.resolveMessage(exception);
		}
		
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("code",    "ERROR"); //임시
		error.put("message", message);
		objectMapper.writeValue(response.getWriter(), error);
	}
	
	
	
	public String resolveMessage(Throwable ex) {
		String message = "Authentication failed.";
		if (ex instanceof BadCredentialsException) {
			message = "아이디 또는 비밀번호가 올바르지 않습니다.";
		} else if (ex instanceof io.jsonwebtoken.security.SecurityException) {
			message = "잘못된 JWT 서명입니다.";
		} else if (ex instanceof MalformedJwtException) {
			message = "잘못된 JWT 서명입니다.";
		} else if (ex instanceof ExpiredJwtException) {
			message = "토큰(세션)의 유효기간 만료되었습니다.";
		} else if (ex instanceof UnsupportedJwtException) {
			message = "지원되지 않는 JWT 토큰입니다.";
		} else if (ex instanceof IllegalArgumentException) {
			message = "JWT 토큰이 잘못되었습니다.";
		} else if (ex instanceof AuthenticationCredentialsNotFoundException) {
			message = "비밀번호가 누락되었습니다.";
		} else if (ex instanceof UsernameNotFoundException) {
			message = "아이디 또는 비밀번호가 올바르지 않습니다.";
		} else if( ex instanceof AuthorizationTokentNotFoundException) {
			message = "보호된 리소스에 접근하기 위한 토큰이 존재하지 않습니다.";
		} else if( ex instanceof ExpiredRefreshTokenException) {
			message = "Refresh 토큰이 만료되었습니다.";
		}
		return message;
	}
}
