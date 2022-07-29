package com.example.springcloudgateway.config;

import com.example.springcloudgateway.exception.RequestAccessDeniedHandler;
import com.example.springcloudgateway.exception.RequestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    /**
     * JWT的鉴权管理器
     */
    @Autowired
    private AuthorizationManager accessManager;

    /**
     * token过期的异常处理
     */
    @Autowired
    private RequestAuthenticationEntryPoint requestAuthenticationEntryPoint;

    /**
     * 权限不足的异常处理
     */
    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    /**
     * 系统参数配置
     */
    @Autowired
    private WhiteListConfig whiteListConfig;

    /**
     * token校验管理器
     */
    @Autowired
    private JwtAuthenticationManager tokenAuthenticationManager;


    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        //认证过滤器，放入认证管理器tokenAuthenticationManager
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());

        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                //白名单直接放行
                .pathMatchers(whiteListConfig.getUrls().stream().toArray(String[]::new)).permitAll()
                //其他的请求必须鉴权，使用鉴权管理器
                .anyExchange().access(accessManager)
                //鉴权的异常处理，权限不足，token失效
/*                .and()
                .exceptionHandling()
                .authenticationEntryPoint(requestAuthenticationEntryPoint)
                .accessDeniedHandler(requestAccessDeniedHandler)*/
                .and()
                // 跨域过滤器
                //.addFilterAt(corsFilter, SecurityWebFiltersOrder.CORS)
                //token的认证过滤器，用于校验token和认证
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}