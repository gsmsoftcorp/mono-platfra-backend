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
    @Lazy
    @Autowired(required = false)
    private SecurityIgnoreProperties securityIgnoreProperties;
    
//    @Lazy
//    @Autowired(required = false)
//    private ServiceAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Lazy
//    @Autowired(required = false)
//    private ServiceAuthenticationEntryPoint authenticationEntryPoint;
//
    @Lazy
    @Autowired(required = false)
    private ServiceAccessDeniedHandler accessDeniedHandler;

    @Lazy
    @Autowired(required = false)
    private JwtUtil jwtUtil;
    
    @Lazy
    @Autowired(required = false)
    private ObjectMapper objectMapper;
    
    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> permitAllEndpointList = Arrays.asList(
            URIPrefix.AUTH_AUTHENTICATION//, URIPrefix.AUTH_REFRESH
        );
        
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)// REST API 방식으로 CSRF 보안 토큰 생성 x
            .addFilterBefore(new AuthFilter(authProvider), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authExceptionHandlerFilter, AuthFilter.class)
//            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)// TODO 인증 실패 핸들링 추가 필요
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 세션 사용 x
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers(URIPrefix.AUTH_AUTHENTICATION, URIPrefix.AUTH_REFRESH)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            ); // 리소스 같은 접근 처리 불가
        
        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        /** 각 마이크로서비스 yml의 gsm.security.ignore.path에 ignore 대상 url을 array형식으로 입력한다(예정) **/
        String[] ignorePath = securityIgnoreProperties.getPath().stream().toArray(String[]::new);
        
        return (web) -> web
            .ignoring()
            .requestMatchers(
                PathRequest.toStaticResources().atCommonLocations() // 정적 리소스는 무시
            )
            // AuthFilter를 적용하지 않을 URL
            .requestMatchers(ignorePath);
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
    
    
    @Configuration
    @Profile({"dev", "local"})
    class DevSecurityConfiguration {
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:8080"
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
