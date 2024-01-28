package com.gsm.platfra.system.security.context;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserContextHandler {

	void handle(UserContext userContext, HttpServletRequest request, HttpServletResponse response);

}