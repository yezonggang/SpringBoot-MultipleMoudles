package utils;

import org.springframework.security.core.Authentication;

/**
 * 获取用户
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
