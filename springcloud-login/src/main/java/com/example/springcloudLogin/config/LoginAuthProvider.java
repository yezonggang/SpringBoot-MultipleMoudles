package com.example.springcloudLogin.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

/**
 * 登录处理
 */
@Data
public class LoginAuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(LoginAuthProvider.class);

    @Autowired
    private UserDetailsService userDetailsService;

    public LoginAuthProvider(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) auth;
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userDetail = userDetailsService.loadUserByUsername(username);
        if (!userDetail.isEnabled()){
            throw new DisabledException("该账号已禁用，请联系管理员");
        }else if (!userDetail.isAccountNonExpired()){
            throw new AccountExpiredException("该账号已过期,请联系管理员");
        }else if(!userDetail.isAccountNonLocked()){
            throw new LockedException("该账号已被锁定，请联系管理员");
        }else if(!userDetail.isCredentialsNonExpired()){
            throw new CredentialsExpiredException("该账号的登录凭证已过期，请重新登录");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(password,userDetail.getPassword())){
            throw  new BadCredentialsException("密码错误请重新输入");
        }
        LoginAuthenticationToken result = new LoginAuthenticationToken(userDetail, authentication.getCredentials(), new HashSet<>());
        result.setDetails(authentication.getDetails());
        return result;
    }

    // supports函数用来指明该Provider是否适用于该类型的认证，如果不合适，则寻找另一个Provider进行验证处理。
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
