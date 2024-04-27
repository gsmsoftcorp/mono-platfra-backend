package com.gsm.platfra.system.security.microservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsm.platfra.system.security.filter.AuthExceptionHandlerFilter;
import com.gsm.platfra.system.security.microservice.filter.AuthFilter;
import com.gsm.platfra.system.security.microservice.provider.AuthProvider;
import com.gsm.platfra.system.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final AuthProvider authProvider;

    private final AuthExceptionHandlerFilter authExceptionHandlerFilter;

    private final ObjectMapper objectMapper;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthFilter authFilter = new AuthFilter(authProvider);
        String[] ignorePath = securityIgnoreProperties().getPath().stream().toArray(String[]::new);
        http
            .cors(Customizer.withDefaults()) // CORS 설정을 명시적으로 적용
            .csrf(AbstractHttpConfigurer::disable)// REST API 방식으로 CSRF 보안 토큰 생성 x
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 세션 사용 x
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authExceptionHandlerFilter, AuthFilter.class)
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(this.authenticationEntryPoint())
                .accessDeniedHandler(this.accessDeniedHandler())
            )
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers(ignorePath).permitAll()    // AuthFilter를 적용하지 않을 URL
                    .anyRequest().authenticated()
            )
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServiceAuthenticationEntryPoint authenticationEntryPoint() {
        return new ServiceAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public ServiceAccessDeniedHandler accessDeniedHandler() {
        return new ServiceAccessDeniedHandler(objectMapper);
    }

    @Bean
    public SecurityIgnoreProperties securityIgnoreProperties() {
        return new SecurityIgnoreProperties();
    }

    @Bean
    public ServiceAuthenticationFailureHandler authenticationFailureHandler() {
        return new ServiceAuthenticationFailureHandler(objectMapper);
    }
}
