package com.gsm.platfra.system.security.filter;

import com.gsm.platfra.exception.custom.AuthTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.gsm.platfra.exception.ExceptionCode.AUTHENTICATION_FAILED;
import static com.gsm.platfra.util.ErrorMessageUtil.makeErrorJson;

@Component
public class AuthExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthTokenException e) {
            response.addHeader("Content-Type", "application/json; charset=UTF-8");
            response.getWriter().write(makeErrorJson(AUTHENTICATION_FAILED.getCode(), AUTHENTICATION_FAILED.getMessage(), e.getMessage()));
        }
    }

}
