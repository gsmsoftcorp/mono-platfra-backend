package com.gsm.platfra.system.security.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* 
* 프로젝트의 SecurityProperties 객체 
*
* @author gyeonghunmin
* @since 1.0 
* 
*/
@Component
@ConfigurationProperties(ignoreInvalidFields = true, prefix = "gsm.security.ignore" )
public class SecurityIgnoreProperties implements InitializingBean{
	
	private Logger log = (Logger) LoggerFactory.getLogger(SecurityIgnoreProperties.class);

	private List<String> path;

	
	@Override
	public void afterPropertiesSet() {
		log.info("[gsm.security.ignore] loaded.");
		log.info("path : {}", path);
		
		if(path == null) {
			path = new ArrayList<>();
		}
//		path.add("/login");
//		path.add("/signup");
//		path.add("/kakao");
//		path.add("/password");
		path.add("/api/account/login");
		path.add("/api/account/signup");
		path.add("/api/account/kakao");
		path.add("/api/account/password");
	}


	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "SecurityIgnorePathProperties [ path=" + path + "]";
	}
	
	
}
