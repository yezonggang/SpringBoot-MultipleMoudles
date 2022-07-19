package com.example.springbootmybatisplus.config.security;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmybatisplus.entity.RefreshTokenEntity;
import com.example.springbootmybatisplus.mapper.RefreshTokenMapper;
import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import com.example.springbootmybatisplus.utils.JsonWebTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功后，对此类进行鉴权
 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JsonWebTokenUtil tokenUtil;
    private UserServiceImpl userServiceImpl;
    private RequestMatcher requestMatcher;
    private RefreshTokenMapper refreshTokenMapper;

    public JwtAuthenticationFilter(JsonWebTokenUtil tokenUtil, UserServiceImpl userServiceImpl, RequestMatcher requestMatcher,RefreshTokenMapper refreshTokenMapper) {
        this.tokenUtil = tokenUtil;
        this.userServiceImpl = userServiceImpl;
        this.requestMatcher = requestMatcher;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //从请求头部获取json web token
        String jwt = request.getHeader(tokenUtil.getHeader());
        if (StringUtils.hasLength(jwt)&&!jwt.equals("null")&&!jwt.equals("undefined")) {
            //从jwt中获取用户名,这里应该考虑过期时间，超过过期时间的话获取不到username
            //TODO
            String username = tokenUtil.getUsernameFromToken(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                LambdaQueryWrapper<RefreshTokenEntity> queryWrapper = new QueryWrapper<RefreshTokenEntity>().lambda().eq(RefreshTokenEntity::getUsename, username);
                RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.selectOne(queryWrapper);
                String tokenInMysql = StringUtils.isEmpty(refreshTokenEntity) ? "null" : refreshTokenEntity.getToken();
                if(jwt.equals(tokenInMysql)){
                    logger.info("JwtAuthenticationFilter,username:"+username);
                    //通过用户名查询
                    UserDetails userDetails = userServiceImpl.loadUserByUsername(username);
                    //创建认证信息
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                            userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }else{
            reject(response);
        }
        filterChain.doFilter(request,response);
    }

    private static void reject(HttpServletResponse response) throws IOException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("code",50014);
        jsonObject.put("data","Token expired, please log in again.");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(jsonObject));
        out.flush();
    }

}
