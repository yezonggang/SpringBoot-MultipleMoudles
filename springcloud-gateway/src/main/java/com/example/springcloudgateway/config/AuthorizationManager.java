package com.example.springcloudgateway.config;


import constant.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 */
@Component
@Slf4j
@AllArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private WhiteListConfig whiteListConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();

        // 1. 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (!ObjectUtils.isNotEmpty(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        //3. 白名单路径直接放行
        List<String> whiteList = whiteListConfig.getUrls();
        for (String ignoreUrl : whiteList) {
            if (pathMatcher.match(ignoreUrl, path)) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        //TODO:从Redis中获取当前路径可访问角色列表
        List<String> authorities = new ArrayList<>();
        //查询表
        authorities.add("/login/logout");
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                //.any(authorities::contains)
                .any(e->{
                        log.info("访问路径：{}", path);
                        log.info("用户角色roleId：{}", e);
                        log.info("资源需要权限authorities：{}", authorities);
                        return authorities.contains(e);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}