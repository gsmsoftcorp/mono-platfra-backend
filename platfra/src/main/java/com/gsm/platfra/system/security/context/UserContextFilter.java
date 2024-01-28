package com.gsm.platfra.system.security.context;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;


public class UserContextFilter extends GenericFilterBean implements InitializingBean {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private List<UserContextHandler> handlers;

	
	@Override
	public void afterPropertiesSet() throws ServletException {
		log.info("[fwk]UserContextFilter init...");
		super.afterPropertiesSet();
	}
	
	
	public void setHandlers(List<UserContextHandler> handlers) {
		this.handlers = handlers;
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if(log.isDebugEnabled()) {
			log.debug("doFilter entered. {}", ((HttpServletRequest) request).getRequestURL());
		}
		
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest   = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			
			try {			
				UserContext userContext = UserContextHolder.getUserContext();
		
				if(log.isDebugEnabled()) {
					log.debug("get requestContext.");
				}
				
				//UserContextFilter에 등록된 UserContextHadler들을 모두 실행한다.
				for (UserContextHandler handler : this.handlers) {
					handler.handle(userContext, httpRequest, httpResponse);
				}
				
				if(filterChain != null){
					filterChain.doFilter(request, response);
				}
	
			}finally {
				UserContextHolder.remove();
				
				if(log.isDebugEnabled()) {
					log.debug("requestContext removed.");
				}
			}
		}
		if(log.isDebugEnabled()) {
			log.debug("doFilter leave.");
		}
	}
}