package com.example.springbootmybatisplus.config.security;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmybatisplus.entity.RefreshTokenEntity;
import com.example.springbootmybatisplus.mapper.RefreshTokenMapper;
import com.example.springbootmybatisplus.service.IRefreshTokenService;
import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import com.example.springbootmybatisplus.utils.ResponseData;
import com.example.springbootmybatisplus.utils.ResponseMsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * spring security 配置类
 * @author yzg
 */
@EnableWebSecurity
public class MasSecurity extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MasSecurity.class);

    @Autowired
    LoginAuthProvider loginAuthProvider;

    @Autowired
    JsonWebTokenUtil jsonWebTokenUtil;
    @Autowired
    IRefreshTokenService refreshTokenService;

    @Autowired
    RefreshTokenMapper refreshTokenMapper;

    @Autowired
    UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域，配置后SpringSecurity会自动寻找name=corsConfigurationSource的Bean
        http.cors();
        http.csrf().disable();
        //当访问接口失败的配置
        http.exceptionHandling().authenticationEntryPoint(new InterfaceAccessException());
        http.authorizeRequests()
                .antMatchers("/login","/refreshToken","/user/getInfo","swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .and().addFilterAt(jsonAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//因为用不到session，所以选择禁用
        http.addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        List<String> uris = new LinkedList<>();
        uris.add("/login");
        List<AntPathRequestMatcher> matchers = uris.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        return new JwtAuthenticationFilter(jsonWebTokenUtil, userService,
                request -> matchers.stream().anyMatch(m -> m.matches(request)),refreshTokenMapper);
    }



    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(new MySuccessHandler());
        filter.setAuthenticationFailureHandler(new MyFailHandler());
        return filter;
    }



    //登录成功的处理类
    class MySuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            List<GrantedAuthority> roles = (List<GrantedAuthority>) details.getAuthorities();
            //登录时同时生成refreshToken，保存到表中
            logger.info("beginnnnnnnnnnnnn");
            RefreshTokenEntity token = new RefreshTokenEntity();
            token.setUsename(details.getUsername());
            String refreshToken = jsonWebTokenUtil.generateRefreshToken(details, roles.get(0).getAuthority());
            token.setToken(refreshToken);
            LambdaQueryWrapper<RefreshTokenEntity> queryWrapper = new QueryWrapper<RefreshTokenEntity>().lambda().eq(RefreshTokenEntity::getUsename, details.getUsername());
            List<RefreshTokenEntity> refreshTokenEntityAll=refreshTokenMapper.selectList(queryWrapper);
            //int countAll = refreshTokenMapper.selectCount(queryWrapper);
            if (refreshTokenEntityAll!=null && refreshTokenEntityAll.size()>0) {
                if(refreshTokenEntityAll.size() == 1){
                    RefreshTokenEntity refreshTokenTemp = refreshTokenMapper.selectOne(queryWrapper);
                    refreshTokenTemp.setToken(refreshToken);
                    refreshTokenMapper.update(refreshTokenTemp, queryWrapper);
                }else{
                    List<RefreshTokenEntity> refreshTokenList = refreshTokenMapper.selectList(queryWrapper);
                    for(RefreshTokenEntity var:refreshTokenList){
                        var.setToken(refreshToken);
                    }
                    refreshTokenService.saveOrUpdateBatch(refreshTokenList);
                }
            } else {
                logger.info("begin to insert token");
                refreshTokenMapper.insert(token);
                logger.info("end to insert token");
            }
            response.setHeader(jsonWebTokenUtil.getHeader(), refreshToken);
/*            Cookie cookie = new Cookie("token", refreshToken);
            cookie.setPath("/");
            response.addCookie(cookie);*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", true);
            jsonObject.put("code",20000);
            jsonObject.put("data",refreshToken);
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(jsonObject));
            out.flush();
            out.close();
            ResponseData.success(response);

        }
    }

    //登录失败的处理
    public class MyFailHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException e) throws IOException, ServletException {
            ResponseMsgUtil.sendFailMsg(e.getMessage(),response);
        }
    }



    // 先认证后鉴权
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthProvider);
    }


}




