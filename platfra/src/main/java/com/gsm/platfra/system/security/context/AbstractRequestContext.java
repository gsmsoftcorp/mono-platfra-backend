package com.gsm.platfra.system.security.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public abstract class AbstractRequestContext implements Serializable{

	private static final long serialVersionUID = 1L;

	private String threadName;//현재 수행중인 ThreadName. 비동기 호출등의 구분을 위해 추가
	
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	// RequestContextUtils or inherited class only can create this object. 
	protected AbstractRequestContext() {
		
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	public boolean containsAttribute(String key) {
		return attributes.containsKey(key);
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	/**
	 * Key 목록을 반환.
	 * 
	 * @return - Map의 keySet() 리턴.
	 */
	public Iterable<String> getKeys() {
		return attributes.keySet();
	}
	
	public void removeAllAttributes() {
		attributes.clear();
	}
	
	public Map<String, Object> getAllAttributes() {
		return attributes;
	}
	
	public void addAttributes(Map<String, Object> attributes) {
		this.attributes.putAll(attributes);
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	

}
