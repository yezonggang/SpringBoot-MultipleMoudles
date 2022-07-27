package com.example.springcloudLogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class LoginSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 短信验证码配置器
     *  所有的配置都可以移步到WebSecurityConfig
     *  builder.authenticationProvider() 相当于 auth.authenticationProvider();
     *  使用外部配置必须要在WebSecurityConfig中用http.apply(SecurityConfig)将配置注入进去
     */
    @Override
    public void configure(HttpSecurity builder) {
        //注入AuthenticationProvider
        LoginAuthProvider loginAuthProvider = new LoginAuthProvider(userDetailsService);
        builder.authenticationProvider(loginAuthProvider);
    }
}
