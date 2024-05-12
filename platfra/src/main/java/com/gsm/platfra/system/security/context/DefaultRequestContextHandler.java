package com.gsm.platfra.system.security.context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsm.platfra.system.security.util.BeanUtils;
import com.gsm.platfra.system.security.util.JwtUtil;
import com.gsm.platfra.util.NetworkUtils;
import com.gsm.platfra.system.security.util.uid.TimeBaseUID;
import com.gsm.platfra.system.security.util.uid.UID;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


public class DefaultRequestContextHandler implements RequestContextHandler, InitializingBean {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    
    private static final String DEF_SYSTEM_PROP_INST_NAME = "PLATFRA";
    
    public final static String FIELD_NAME_LOGIN_USER = "userId";
    public final static String FIELD_NAME_LOGIN_PASSWORD = "password";
    public final static String FIELD_NAME_TWO_FACTOR_CERT_NO = "twoFactorCertNo";
    public final static String FIELD_NAME_OTP_CERT_NO = "certNo";
    
    private final static String FIELD_NAME_LOGIN_DEVICE_ID = "deviceId";
    private final static String FIELD_MOBILE_TOKEN_NO = "mobileTokenNo";
    public final static String FIELD_NAME_USER_AGENT = "User-Agent";
    private final static String ALLOWED_MOBILE_USER_AGENT = "Android,webOS,iPhone,iPad,iPod,BlackBerry,Windows Phone";
    
    private UID uid;
    
    @Value("${spring.application.name:platfra}")
    private String instanceId;
    
    public void afterPropertiesSet() {
        if (uid == null) {
            uid = new TimeBaseUID(instanceId == null ? DEF_SYSTEM_PROP_INST_NAME : instanceId);
        }
    }

    public void handle(RequestContext requestContext, HttpServletRequest request, HttpServletResponse response) {
        
        long requestTime = System.currentTimeMillis();
        String requestId = generateRequestId();
        
        if (log.isDebugEnabled()) {
            log.debug("requestTime : {}", requestTime);
            log.debug("requestId : {}", requestId);
        }
        
        //RequestInfo
        requestContext.setAttribute("requestTime", requestTime);
        requestContext.setAttribute("requestId", requestId);
        
        UserContext userContext =
            Optional.ofNullable(UserContextUtil.getUserContext()).orElse(new UserContext());
        
        String accessToken = JwtUtil.resolveToken(request);
        log.info("accessToken = " + accessToken);
//        if (StringUtils.isEmpty(accessToken)) {
//            if (log.isDebugEnabled()) {
//                log.debug("accessToken is empty.");
//            }
//            userContext.setUserIp(Optional.ofNullable(NetworkUtils.getClientIP(request)).orElse(""));
//            userContext.setUserId("");
//
//            // 인증정보 없을 때 RequestContext에 기본정보 세팅
//            ObjectMapper mapper = new ObjectMapper();
//
//            Map<String, Object> loginRequest;
//
//            String requestURI = request.getRequestURI();
//            log.info("reqestURI = " + requestURI);
//
//            try {
//                loginRequest = mapper.readValue(request.getReader(), new TypeReference<Map>() {
//                });
//                String username = Optional.ofNullable(loginRequest.get(FIELD_NAME_LOGIN_USER)).map(o -> o.toString()).orElseThrow(() -> new UsernameNotFoundException("Username NotFound"));
//                String otpCertNo = Optional.ofNullable(loginRequest.get(FIELD_NAME_OTP_CERT_NO)).map(o -> o.toString()).orElse("");
//                String twoFactorCertNo = Optional.ofNullable(loginRequest.get(FIELD_NAME_TWO_FACTOR_CERT_NO)).map(o -> o.toString()).orElse("");
//                String password = Optional.ofNullable(loginRequest.get(FIELD_NAME_LOGIN_PASSWORD)).map(o -> o.toString()).orElse("");
//                String deviceId = Optional.ofNullable(loginRequest.get(FIELD_NAME_LOGIN_DEVICE_ID)).map(o -> o.toString()).orElse("");
//                String mobileTokenNo = Optional.ofNullable(loginRequest.get("mobileTokenNo")).map(o -> o.toString()).orElse("");
//                String userAgent = Optional.ofNullable(request.getHeader(this.FIELD_NAME_USER_AGENT)).map(o -> o.toString()).orElse("");
//
//                RequestContextUtil.put(FIELD_NAME_LOGIN_USER, username);
//
//                if (!StringUtils.isEmpty(otpCertNo)) {
//                    RequestContextUtil.put(FIELD_NAME_OTP_CERT_NO, otpCertNo);
//                }
//
//                if (!StringUtils.isEmpty(twoFactorCertNo)) {
//                    RequestContextUtil.put(FIELD_NAME_TWO_FACTOR_CERT_NO, twoFactorCertNo);
//                }
//
//                RequestContextUtil.put(FIELD_NAME_LOGIN_PASSWORD, password);
//                // 모바일
//                RequestContextUtil.put(FIELD_NAME_LOGIN_DEVICE_ID, deviceId);
//                RequestContextUtil.put(FIELD_MOBILE_TOKEN_NO, mobileTokenNo);
//                RequestContextUtil.put(FIELD_NAME_USER_AGENT, userAgent);
//
//            } catch (IOException e) {
//                log.error("request readValue failed : {}", e);
//            }
//        } else {
            //JwtAuthenticationInfo
            try {
                JwtUtil jwtUtil = (JwtUtil) BeanUtils.getBean(JwtUtil.class);
                if (jwtUtil != null) {
                    if (!StringUtils.isEmpty(accessToken)) {
                        Claims claims = jwtUtil.getAllClaimsFromToken(accessToken);
                        userContext.setAccessToken(accessToken);
                        userContext.setUserId((String) claims.get("userId"));
                        userContext.setSub(claims.getSubject());
                        userContext.setIat(claims.getIssuedAt());
                        userContext.setExp(claims.getExpiration());
                        userContext.setUserIp(Optional.ofNullable(NetworkUtils.getClientIP(request)).orElse(""));
                        
                        if (log.isDebugEnabled()) {
                            log.debug("userContext : {}", userContext);
                            log.debug("from claims");
                        }
                        claims.forEach((key, value) -> {
                            if (log.isDebugEnabled()) {
                                log.debug("key : {}  \t\t value : {}", key, value);
                            }
                            requestContext.setAttribute(key, value.toString());
                        });
                    }
                }
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug("RequestContextHandler handling error because of {}", e);
                }
            }
            
//        }
        UserContextUtil.setUserContext(userContext);
        
    }
    
    protected String generateRequestId() {
        return uid.getUID();
    }
}