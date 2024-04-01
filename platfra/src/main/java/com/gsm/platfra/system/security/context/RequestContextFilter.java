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


public class RequestContextFilter extends GenericFilterBean implements InitializingBean {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private List<RequestContextHandler> handlers;


	@Override
	public void afterPropertiesSet() throws ServletException {
		log.info("[fwk]RequestContextFilter init...");
		super.afterPropertiesSet();
	}


	public void setHandlers(List<RequestContextHandler> handlers) {
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
				RequestContext requestContext = RequestContextHolder.getRequestContext();

				if(log.isDebugEnabled()) {
					log.debug("get requestContext.");
				}

				//RequestContextFilter에 등록된 RequestContextHadler들을 모두 실행한다.
				for (RequestContextHandler handler : this.handlers) {
					handler.handle(requestContext, httpRequest, httpResponse);
				}

				if(filterChain != null){
					filterChain.doFilter(request, response);
				}

			}finally {
				RequestContextHolder.remove();

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