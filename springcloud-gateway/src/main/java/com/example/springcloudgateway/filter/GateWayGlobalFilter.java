package com.example.springcloudgateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springcloudLogin.service.LoginClientServiceImpl;
import com.nimbusds.jose.JWSObject;
import constant.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import response.ResponseData;

import java.nio.charset.Charset;
import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 */
@Component
public class GateWayGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private LoginClientServiceImpl loginClientService;
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
            //feign调用查询是否存在当前有效的token值，若存在刷新token有效时间，若不存在，请重新登录
            if (!StringUtils.isEmpty(token)){
                String username = jsonObject.getString("user_name");
                ResponseData responseData = loginClientService.checkLogin(username);
                if (!token.equals(responseData.getData())){
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.OK);
                    response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    String body= "无效token";
                    DataBuffer buffer =  response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
                    return response.writeWith(Mono.just(buffer));
                };
            }

            // token有效不在黑名单中，request写入JWT的载体信息传递给其他服务
            request = exchange.getRequest().mutate().header(SecurityConstants.JWT_PAYLOAD_KEY, jsonObject.toJSONString()).build();
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