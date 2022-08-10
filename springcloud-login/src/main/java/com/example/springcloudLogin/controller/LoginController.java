package com.example.springcloudLogin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springcloudLogin.entity.OauthTokenEntity;
import com.example.springcloudLogin.vo.Oauth2TokenVO;
import constant.SecurityConstants;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import response.ResponseData;
import utils.JwtUtils;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class LoginController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseData postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenVO oauth2TokenVO = Oauth2TokenVO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();

        return ResponseData.success(oauth2TokenVO);
    }

    @RequestMapping(value = "/token2", method = RequestMethod.POST)
    public ResponseData postAccessToken(Principal principal, @RequestBody OauthTokenEntity oauthTokenEntity) throws HttpRequestMethodNotSupportedException {
        Map<String,String> parameters = new HashMap();
        System.out.println(String.format("oauthTokenEntity:%s",oauthTokenEntity));
        parameters.put("username", oauthTokenEntity.getUserName());
        parameters.put("password", oauthTokenEntity.getPassWord());
        parameters.put("client_id", oauthTokenEntity.getClientId());
        parameters.put("scope", oauthTokenEntity.getScope());
        parameters.put("client_secret", oauthTokenEntity.getClientSecret());
        parameters.put("grant_type", oauthTokenEntity.getGrantType());
        System.out.println(String.format("parameters:%s",parameters));
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenVO oauth2TokenVO = Oauth2TokenVO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();

        return ResponseData.success(oauth2TokenVO);
    }

    @ApiOperation(value = "注销")
    @DeleteMapping("/logout")
    public ResponseData logout() {
        JSONObject payload = JwtUtils.getJwtPayload();
        //String jti = payload.getString(SecurityConstants.JWT_JTI); // JWT唯一标识
        Long expireTime = payload.getLong(SecurityConstants.JWT_EXP); // JWT过期时间戳(单位：秒)
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                //TODO:

                log.info("登录退出，假设实现了token注销");
            }
        } else { // token 永不过期则永久加入黑名单
            log.info("登录退出，假设实现了token注销");
        }
        return ResponseData.success("注销成功");
    }
}
