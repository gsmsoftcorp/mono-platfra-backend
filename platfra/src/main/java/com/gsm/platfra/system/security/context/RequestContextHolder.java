package com.gsm.platfra.system.security.context;


import com.gsm.platfra.system.security.util.ClassUtils;

public class RequestContextHolder {
	
	private static final String PROP_REQUEST_CONTEXT_CLASS_NAME = RequestContext.class.getName();
	
	private static ThreadLocal<RequestContext> requestContext = new ThreadLocal<RequestContext>();


	/**
	 *  현재쓰레드의 ThredLocal영역에 RequestContext객체를 생성하고 리턴한다.
	 * @return - ThreadLocal 영역에 생성된 RequestContext 객체.
	 */
	public static RequestContext getRequestContext() {
		
		if (requestContext.get() == null) {
		
			//String requestContextClassName = PropertyServiceHolder.getProperty(PROP_REQUEST_CONTEXT_CLASS_NAME);
			String requestContextClassName = PROP_REQUEST_CONTEXT_CLASS_NAME;
			
			if(requestContextClassName == null)	{
			
				RequestContext ctx = new RequestContext();
				ctx.setThreadName(Thread.currentThread().getName());
				requestContext.set(ctx);
				
			}else {
				
				RequestContext ctx = ClassUtils.<RequestContext>createInstance(requestContextClassName);
				ctx.setThreadName(Thread.currentThread().getName());
				requestContext.set(ctx);
				
			}
		}
		return requestContext.get();
	}

	
	/**
	 * ThreadLocal 영역에 생성된 RequestContext 객체를 제거.
	 *
	 * <p>
	 * 이 메서드는 getRequestContext를 통해 RequestContext객체를 최초 생성한 곳에서 반드시 호출되어야 합니다.
	 */
	public static void remove() {
		requestContext.remove();
	}

}