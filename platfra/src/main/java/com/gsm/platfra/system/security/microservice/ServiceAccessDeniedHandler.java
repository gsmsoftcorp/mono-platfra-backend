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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *  AccessDecisionManager(Voter) 결정 후, 권한이 없는 리소스에 접근시 AccessDenied된 이벤트에 대한 핸들링을 처리한다.
 * @see SecurityConfig
 */
@Component
public class ServiceAccessDeniedHandler implements AccessDeniedHandler {
	private final Logger log = LoggerFactory.getLogger(ServiceAccessDeniedHandler.class);
	private final ObjectMapper objectMapper;
	public ServiceAccessDeniedHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public void handle(HttpServletRequest request
					 , HttpServletResponse response
					 , AccessDeniedException accessDeniedException) throws IOException {
		
		log.error("[ServiceAccessDeniedHandler] : Access denied.");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		Map<String, Object> deniedResponse = new HashMap<String, Object>();
		deniedResponse.put("code",    "403");
		deniedResponse.put("message", "권한이 없는 리소스에 접근을 시도하였습니다.");
		objectMapper.writeValue(response.getWriter(), deniedResponse);
	}

}
