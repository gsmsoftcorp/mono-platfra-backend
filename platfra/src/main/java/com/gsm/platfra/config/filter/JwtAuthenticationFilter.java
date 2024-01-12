package com.gsm.platfra.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.security.dto.LoginDto;
import com.gsm.platfra.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @SneakyThrows // 예외처리 무시
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) { // 인증을 시도하는 로직
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    // provider측(OAuth2) 인증에 성공할 경우 (Spring Security에서 자동으로) 호출되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {
        TAccount tAccount = (TAccount) authResult.getPrincipal();

        if(!tAccount.getTAccountStatus().equals(TAccount.TAccountStatus.ACTIVE)){
            tAccount.setTAccountStatus(TAccount.TAccountStatus.ACTIVE);
        }

        String accessToken = tokenService.delegateAccessToken(tAccount);
        String refreshToken = tokenService.delegateRefreshToken(tAccount);

        response.setHeader("Authorization", accessToken);
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
