package com.gsm.platfra.system.security.context;

import java.util.Map;

public class RequestContextUtil {
	
	
	public static void put(String key, Object value) {
		RequestContextHolder.getRequestContext().setAttribute(key, value);
	}

	
	public static Object get(String key) {
		return RequestContextHolder.getRequestContext().getAttribute(key);
	}

	
	public static void clearAll() {
		RequestContextHolder.getRequestContext().removeAllAttributes();
	}

	
	public static Map<String, Object> getAll() {
		return RequestContextHolder.getRequestContext().getAllAttributes();
	}
	
	
}