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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 보호된 리소스에 접근시, SecurityContext에 인증된 사용자가 없을경우
 * @see SecurityConfig
 */
@Component
public class ServiceAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private final Logger log = LoggerFactory.getLogger(ServiceAuthenticationEntryPoint.class);
	private ObjectMapper objectMapper;
	
	public ServiceAuthenticationEntryPoint(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		log.error("[ServiceAuthenticationEntryPoint] : Authentication Failed.");

		// 401
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		Map<String, Object> commenceResponse = new HashMap<String, Object>();
		commenceResponse.put("code", "401 ERROR");
		commenceResponse.put("message", "API Access was detected from unauthenticated user.");
		objectMapper.writeValue(response.getWriter(), commenceResponse);
	}

}
