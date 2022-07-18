package com.example.springbootmybatisplus.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 默认的用户名/密码提取是通过request中的getParameter来提取的
 * 该过滤器实现了从json获取用户名和密码
 * @author yzg
 */
@Order(-2)
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JsonAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        final String APPLICATION_JSON_UTF8_VALUE_MY = "application/json;charset=UTF-8";

        //判断请求类型是否是json
        logger.info("request.getContentType():"+request.getContentType());
        if ( request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                ||request.getContentType().equals(APPLICATION_JSON_UTF8_VALUE_MY)  ){
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authenticationToken = null;
            try {
                InputStream is = request.getInputStream();
                Map<String,String> authenticationBean = mapper.readValue(is,Map.class);
                authenticationToken = new UsernamePasswordAuthenticationToken(authenticationBean.get("username"),
                        authenticationBean.get("password"));
                logger.info("jinqulexsfdfdfsd--------"+authenticationBean);
            }catch (IOException e){
                e.printStackTrace();
                authenticationToken = new UsernamePasswordAuthenticationToken("","");
            }
            setDetails(request,authenticationToken);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }else {
            request.getContentType();
            logger.info("meijinqu");
            return super.attemptAuthentication(request, response);
        }

    }
}
