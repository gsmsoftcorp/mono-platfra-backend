package com.gsm.platfra.system.security.util;

import com.gsm.platfra.system.security.context.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


public class SystemUtils {
	private static final Logger log = LoggerFactory.getLogger(SystemUtils.class);
	
	public static String[] getProfiles() {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		return applicationContext.getEnvironment().getActiveProfiles();
	}
	
	public static String getProfileFirstOnly() {
		String[] profiles = SystemUtils.getProfiles();
		if(profiles.length == 0) {
			return "";
		}else {
			return profiles[0];
		}
	}
	
	public static String[] getDefaultProfiles() {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		return applicationContext.getEnvironment().getDefaultProfiles();
	}
	
}
