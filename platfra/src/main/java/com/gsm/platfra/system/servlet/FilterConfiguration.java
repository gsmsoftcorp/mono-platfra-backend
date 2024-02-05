package com.gsm.platfra.system.servlet;

import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class FilterConfiguration {
    @Bean
    @ConditionalOnMissingFilterBean
    public FilterRegistrationBean<ContentCachingFilter> contentCachingFilter() {
        Set<String> ignoreUris = new HashSet<>();
        ignoreUris.add("/**/file/upload/**");
        ignoreUris.add("/**/upload/**");
        ignoreUris.add("/**/uploads/**");
        ignoreUris.add("/**/uploadz/**");
        ignoreUris.add("/**/upload**/**");

        FilterRegistrationBean<ContentCachingFilter> registrationBean = new FilterRegistrationBean<>(new ContentCachingFilter(ignoreUris));
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        registrationBean.setUrlPatterns(List.of("/*"));
        return registrationBean;
    }
}
