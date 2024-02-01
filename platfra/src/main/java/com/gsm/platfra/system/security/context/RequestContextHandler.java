package com.gsm.platfra.system.security.context;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RequestContextHandler {
	void handle(RequestContext requestContext, HttpServletRequest request, HttpServletResponse response);

}