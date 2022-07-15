package com.example.springcloudLogin.config;

import com.example.springcloudLogin.vo.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户ID设置到JWT中
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        if (principal instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) principal;
            info.put("userId", securityUser.getId());
            info.put("username", securityUser.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
            return accessToken;
        }
        return null;
    }
}
