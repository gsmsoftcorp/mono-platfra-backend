package com.gsm.platfra.system.security.context;


public class UserContextHolder {
	private static ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();


	/**
	 *  현재쓰레드의 ThredLocal영역에 UserContext 객체를 생성하고 리턴한다.
	 * @return - ThreadLocal 영역에 생성된 UserContext 객체.
	 */
	public static UserContext getUserContext() {
		UserContext ctx = new UserContext();
		ctx.setThreadName(Thread.currentThread().getName());
		userContext.set(ctx);
		return userContext.get();
		
	}

	
	/**
	 * ThreadLocal 영역에 생성된 UserContext 객체를 제거.
	 *
	 * <p>
	 * 이 메서드는 getUserContext를 통해 UserContext 객체를 최초 생성한 곳에서 반드시 호출되어야 합니다.
	 */
	public static void remove() {
		userContext.remove();
	}

}