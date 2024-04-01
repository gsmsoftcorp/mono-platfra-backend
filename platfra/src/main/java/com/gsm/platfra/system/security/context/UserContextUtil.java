package com.gsm.platfra.system.security.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserContextUtil {
	
	private final static Logger log = LoggerFactory.getLogger(UserContextUtil.class);
	

	public static String HELD_NAME_USER_CONTEXT = "USER-CONTEXT";
	
//	public final static Set<String> USER_CONTEXT_KEY_SET;
//	
//	static {
//		Set<String> keySet = new HashSet();
//		Field[] fields     = UserContext.class.getClass().getDeclaredFields();
//		for(Field f : fields) {
//			try {
//				f.setAccessible(true);
//				keySet.add(f.getName());
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//				log.error("unmodifiableClaims generate failed : {}", e);
//			}
//		}
//		
//		USER_CONTEXT_KEY_SET = Collections.unmodifiableSet(keySet);
//	}
	
	
	public static UserContext getUserContext() {
		if(RequestContextUtil.get(HELD_NAME_USER_CONTEXT) != null) {
			return (UserContext) RequestContextHolder.getRequestContext().getAttribute(HELD_NAME_USER_CONTEXT);
		}else {
			return null;
		}
	}
	
	
	public static void setUserContext(UserContext userContext) {
		RequestContextUtil.put(HELD_NAME_USER_CONTEXT, userContext);
	}
	
}
