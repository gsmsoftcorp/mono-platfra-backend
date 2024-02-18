package com.gsm.platfra.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkUtils {

	private static final Logger log = LoggerFactory.getLogger(NetworkUtils.class);

	public static String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		if (ip != null && ip.length() > 0) {
			if (ip.indexOf(',') > 0) {
				ip = ip.substring(0, ip.indexOf(','));
			}
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			//ip = request.getHeader("X-Envoy-External-Address");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.printf("X-Real-IP >>>> " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	
	
	public static Map<String, List<String>> splitQuery(String queryString) {
		
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		
		final String[] pairs = queryString.split("&");
		
		try {
			for (String pair : pairs) {
				final int idx = pair.indexOf("=");
		
				String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
				
				if (!query_pairs.containsKey(key)) {
					query_pairs.put(key, new LinkedList<String>());
				}
				
				final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
				
				query_pairs.get(key).add(value);
			}

			return query_pairs;
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 해당서버의 호스트네임을 가져온다.
	 * @return
	 */
	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "";
		}
	}
	
	
	/**
	 * 해당서버의 아이피주소를 가져온다.
	 * @return
	 */
	public static String getHostAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "";
		}
	}
	
	
	/**
	 * 해당 요청의 프로토콜 scheme을 가져온다.
	 * @param request
	 * @return
	 */
	public static String getProtocol(HttpServletRequest request) {
		return request.getScheme();
	}
	
	
	/**
	 * 해당요청의 서버명을 가져온다.
	 * @param request
	 * @return
	 */
	public static String getServerName(HttpServletRequest request) {
		return request.getServerName();
	}
	
	
	/**
	 * 해당요청의 서비스중인 포트를 가져온다.
	 * @param request
	 * @return
	 */
	public static int getServerPort(HttpServletRequest request) {
		return request.getServerPort();
	}
	
	
	/**
	 * 해당Request의 서버주소를 가져온다.</br>
	 * 마이크로서비스의 스프링클라우드적용으로 인스턴스ID가 반환된다. 
	 * 따라서 유레카에서 관리되는 마이크로서비스의 연계시에 사용한다.
	 * @param request
	 * @return
	 */
	public static String getBaseUrlByServerName(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	
	/**
	 * 해당 request의 url에서 uri를 제외한 BaseUrl을 가져온다.
	 * @param request
	 * @return
	 */
	public static String getBaseUrlByUriRemoval(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getRequestURI(), "");
	}
	
	
	/**
	 * 환경별 서비스의 URL을 가져온다. 
	 * ex) (local) http://localhost:8080
	 *     (dev) http://
	 *     (prod) https://www.platfra.com
	 * @param request
	 * @return
	 */
	public static String getServiceUrlByProfile(HttpServletRequest request) {
		
		String serviceUrl = request.getScheme() + "://" + request.getServerName();
		
		switch(SystemUtils.getProfileFirstOnly()) {
		case "local":
			serviceUrl = request.getScheme() + "://localhost:" + 8080;
			break;
		case "dev":
			serviceUrl = serviceUrl;
			break;
		case "prod":
			serviceUrl = serviceUrl;
			break;
		default:
			serviceUrl = serviceUrl;
			break;
		}
		
		return serviceUrl;
	}
	
	
	/**
	 * 개발테스트시 지정된 환경의 Profile 서비스주소를 가져온다.(서비스주소가 하드코딩됨으로 부득이한 경우만 사용)
	 * @param request
	 * @param defaultDevEnv
	 * @return
	 */
	public static String getServiceUrlByProfile(HttpServletRequest request, String defaultDevEnv) {

		String serviceUrl = request.getScheme() + "://" + request.getServerName();
		String profile    = SystemUtils.getProfileFirstOnly();
		
		if( (defaultDevEnv.equals("dev") || defaultDevEnv.equals("local")) && defaultDevEnv.equals(profile)) {
			profile = defaultDevEnv;
		}
		
		switch(profile) {
		case "local":
			serviceUrl = request.getScheme() + "://localhost:" + 8080;
			break;
		case "dev":
			serviceUrl = request.getScheme() + "://";
		case "prod":
			//serviceUrl = serviceUrl;
			serviceUrl = "https://www.platfra.com";
		default:
			serviceUrl = serviceUrl;
			break;
		}
		
		return serviceUrl;
	}

}
