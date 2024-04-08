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

    @Lazy
    @Autowired
    private CorsConfigurationSource corsConfigurationSource; // DevSecurityConfiguration 또는 ProdSecurityConfiguration에서 정의한 CorsConfigurationSource 빈을 주입받습니다.


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource)) // CORS 설정을 명시적으로 적용
            .csrf(AbstractHttpConfigurer::disable)// REST API 방식으로 CSRF 보안 토큰 생성 x
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 세션 사용 x
            .authorizeHttpRequests(
                auth -> auth
                    .anyRequest().authenticated()
            ) // 리소스 같은 접근 처리 불가
            .addFilterBefore(new AuthFilter(authProvider), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authExceptionHandlerFilter, AuthFilter.class)
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(this.authenticationEntryPoint())
                .accessDeniedHandler(this.accessDeniedHandler())
            )

        ;
        return http.build();
    }


    /** 각 마이크로서비스 yml의 security.ignore.path에 ignore 대상 url을 array형식으로 입력한다(예정) **/
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] ignorePath = securityIgnoreProperties().getPath().stream().toArray(String[]::new);

        return (web) -> web
            .ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())   // 정적 리소스는 무시
            .requestMatchers(ignorePath)    // AuthFilter를 적용하지 않을 URL
            ;
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

    @Configuration
    @Profile({"dev", "local"})
    class DevSecurityConfiguration {
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000"
            ));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }


    @Configuration
    @Profile("prod")
    class ProdSecurityConfiguration {
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(
                "http://www.platfra.com"
                , "https://www.platfra.com"

            ));
            configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

    }
}
