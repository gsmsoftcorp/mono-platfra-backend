package com.gsm.platfra.config.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // BEGIN LoginCheck 인터셉터 넣기 전 소스
//        registry.addInterceptor(new CommonFrontInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/resources/**", "/static/**");
        // E N D LoginCheck 인터셉터 넣기 전 소스

        // 인터셉터에서 서비스등(주입받을거) 쓸려면 bean으로 생성해서 넣기~
//        registry.addInterceptor(new CommonInterceptor()).order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/resources/**", "/static/**");;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addResourceHandler("/resources/**", "/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(20)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}
