package com.example.springcloudgateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import constant.SecurityConstants;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 */
@Component
public class GateWayGlobalFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        try {
            if (StringUtils.isEmpty(token)) {
                return chain.filter(exchange);
            }

            // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
            token = StringUtils.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, SecurityConstants.STRING_NULL);
            String payload = JWSObject.parse(token).getPayload().toString();
            JSONObject jsonObject = JSON.parseObject(payload);
            // token有效不在黑名单中，request写入JWT的载体信息传递给其他服务
            request = exchange.getRequest().mutate().header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(jsonObject.toJSONString(), "UTF-8")).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}