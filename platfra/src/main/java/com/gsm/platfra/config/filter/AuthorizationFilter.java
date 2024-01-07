package com.gsm.platfra.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

/**
 * HTTP 기본 인증 헤더를 감시하고 이를 처리하는 역할의 필터이다.
 *
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private JWTtoken jwtToken;

    AuthenticationManager authenticationManager;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    /*
     * 쿠키 인증 토큰을 검사한다.
     * 만약 토큰 및 헤더에 대한 검사에 실패한다면,
     * AuthenticationEntryPoint에 위임하거나 혹은 HttpResponse에 적절한
     * 상태코드와 메시지를 담아서 리턴해준다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("doFilterInternal :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        // 세션에서 ID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        super.doFilterInternal(httpServletRequest, httpServletResponse, chain);
    }

    /*
     * 성공시 처리 메소드
     */
    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                              Authentication authentication) throws IOException {
        logger.debug("onSuccessfulAuthentication");

        super.onSuccessfulAuthentication(httpServletRequest, httpServletResponse, authentication);
    }

    /*
     * 실패시 처리 메소드
     */
    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                AuthenticationException failed) throws IOException {
        logger.debug("onUnsuccessfulAuthentication");
        super.onUnsuccessfulAuthentication(httpServletRequest, httpServletResponse, failed);
    }
}
