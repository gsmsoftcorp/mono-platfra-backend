package com.gsm.platfra.system.security.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class UserContextUtil {
	private final static Logger log = LoggerFactory.getLogger(UserContextUtil.class);

	public static String USER_CONTEXT = "USER-CONTEXT";
	
	public static UserContext getUserContext() {
		if(UserContextHolder.getUserContext().getAttribute(USER_CONTEXT) != null) {
			return (UserContext)UserContextHolder.getUserContext().getAttribute(USER_CONTEXT);
		}else {
			return null;
		}
	}
	
	public static void setUserContext(UserContext userContext) {
		UserContextHolder.getUserContext().setAttribute(USER_CONTEXT, userContext);
	}
	
	
	public static void put(String key, Object value) {
		UserContextHolder.getUserContext().setAttribute(key, value);
	}
	
	
	public static void clearAll() {
		UserContextHolder.getUserContext().removeAllAttributes();
	}
	
	
	public static Map<String, Object> getAll() {
		return UserContextHolder.getUserContext().getAllAttributes();
	}
}
