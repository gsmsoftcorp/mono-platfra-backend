package com.gsm.platfra.system.security.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;



/**
 * 프레임워크에서 컨텍스트와 관련된 스프링 빈을 관리한다.
 */
@Configuration
public class ContextConfig implements InitializingBean {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("[core-fwk]ContextConfig constructed.");
		}
	}

	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new ApplicationContextProvider();
	}

	@Bean
	public RequestContextFilter fwkRequestContextFilter() {
		List<RequestContextHandler> handlers = new ArrayList();
		handlers.add(requestContextHandler());

		RequestContextFilter requestContextFilter = new RequestContextFilter();
		requestContextFilter.setHandlers(handlers);

		return requestContextFilter;
	}

	@Bean
	public DefaultRequestContextHandler requestContextHandler() {
		return new DefaultRequestContextHandler();
	}

}