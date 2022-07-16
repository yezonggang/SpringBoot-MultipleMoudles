package com.example.springbootmybatisplus.utils;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 获取用户
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
