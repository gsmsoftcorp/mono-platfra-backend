package com.gsm.platfra.system.security.util;

import com.gsm.platfra.system.security.provider.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;


/**
 * 
 * ApplicationContext를 이용하여 일반 자바클래스에서도 스프링 빈을 사용할 수 있도록 도와준다.
 *
 * @author gyeonghunmin
 * @since 1.0 
 * @see ApplicationContextProvider
 */
public class BeanUtils {
	
	
	public static Object getBean(Class<?> classType) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		return applicationContext.getBean(classType);
	}
	
	
	public static Object getBean(String beanName) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		return applicationContext.getBean(beanName);
	}

}