package com.example.springbootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserInterceptorConfig implements WebMvcConfigurer {
/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthUserInterceptor())
                .addPathPatterns("/**");
    }*/
}

