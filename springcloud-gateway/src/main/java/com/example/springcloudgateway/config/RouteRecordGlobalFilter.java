package com.example.springcloudgateway.config;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import utils.JsonUtil;


import java.net.URI;
import java.net.URLEncoder;

@Slf4j
@Component
public class RouteRecordGlobalFilter implements GlobalFilter, Ordered {

	@SneakyThrows
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// RouteToRequestUrlFilter会把实际路由的URL通过该属性保存
		ServerHttpRequest request = exchange.getRequest();
		URI proxyRequestUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
		long start = System.currentTimeMillis();

		// 非JWT放行不做后续解析处理
		String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
		if (ObjectUtils.isEmpty(token) || !StringUtils.startsWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
			log.info("无token实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri);
			return chain.filter(exchange);
		}
			// 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
		token = StringUtils.replace(token, SecurityConstants.JWT_PREFIX,SecurityConstants.STRING_NULL);
		String payload = JWSObject.parse(token).getPayload().toString();
		//TODO:黑名单设置

		// token有效不在黑名单中，request写入JWT的载体信息传递给其他服务
		request = exchange.getRequest().mutate()
				.header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
				.build();
		exchange = exchange.mutate().request(request).build();
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {


			long end = System.currentTimeMillis();
			log.info("实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri, (end - start));
		}));
	}

	@Override
	public int getOrder() {
		// 优先级设为最低，先让RouteToRequestUrlFilter先调用
		return Ordered.LOWEST_PRECEDENCE;
	}
}
