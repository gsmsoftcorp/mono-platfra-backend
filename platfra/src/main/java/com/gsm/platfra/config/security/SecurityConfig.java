package com.gsm.platfra.config.security;

import com.gsm.platfra.config.filter.AuthFilter;
import com.gsm.platfra.config.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthProvider tokenProvider;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API 방식으로 CSRF 보안 토큰 생성 x
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                // 세션 사용 x
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new AuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        auth ->
                                // Todo : 테스트용으로 모든 요청 허용
                                auth.requestMatchers("/login/**", "/file/**").permitAll()
                                                .anyRequest().authenticated()
//                                        auth.anyRequest().permitAll()
                );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
