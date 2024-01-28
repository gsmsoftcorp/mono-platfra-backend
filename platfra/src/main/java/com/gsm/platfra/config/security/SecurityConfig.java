package com.gsm.platfra.config.security;

import com.gsm.platfra.system.security.filter.AuthFilter;
import com.gsm.platfra.system.security.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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

    private final AuthProvider authProvider;

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
                .addFilterBefore(new AuthFilter(authProvider), UsernamePasswordAuthenticationFilter.class)
                // 리소스 같은 접근 처리 불가
                .authorizeHttpRequests(
                        auth ->
                                // Todo : 테스트용으로 모든 요청 허용
//                                auth.requestMatchers("/login/**").permitAll()
//                                                .anyRequest().authenticated()
                                        auth.anyRequest().permitAll()
                );
        
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations() // 정적 리소스는 무시
                )
                // AuthFilter를 적용하지 않을 URL
                .requestMatchers(
                        "/login", "/signup"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
