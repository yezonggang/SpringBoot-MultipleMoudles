package com.example.springcloudLogin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcloudLogin.entity.OauthTokenEntity;
import com.example.springcloudLogin.entity.RefreshTokenEntity;
import com.example.springcloudLogin.entity.UserEntity;
import com.example.springcloudLogin.service.impl.ClientDetailsServiceImpl;
import com.example.springcloudLogin.service.impl.RefreshTokenServiceImpl;
import com.example.springcloudLogin.service.impl.UserServiceImpl;
import com.example.springcloudLogin.vo.LoginInfoVO;
import constant.SecurityConstants;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import response.ResponseData;
import utils.JsonUtil;
import utils.JwtUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody OauthTokenEntity oauthTokenEntity) throws HttpRequestMethodNotSupportedException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String clientId = requestAttributes.getRequest().getHeader("client_id");
        String grant_type = requestAttributes.getRequest().getHeader("grant_type");
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        //信息分装
        oauthTokenEntity.setClient_id(clientId);
        oauthTokenEntity.setClient_secret(clientDetails.getClientSecret());
        oauthTokenEntity.setGrant_type(grant_type);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(clientDetails.getClientId(), null,new ArrayList<>());
        Map<String, String> parameters = null;
        try {
            parameters = JsonUtil.convert(oauthTokenEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(usernamePasswordAuthenticationToken, parameters).getBody();
        //将token落库
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setUsename(oauthTokenEntity.getUsername());
        refreshTokenEntity.setToken(oAuth2AccessToken.getValue());
        refreshTokenService.insertToken(refreshTokenEntity);
        return ResponseData.success(oAuth2AccessToken.getValue());
    }

    @ApiOperation(value = "注销")
    @DeleteMapping("/logout")
    public ResponseData logout() {
        JSONObject payload = JwtUtils.getJwtPayload();
        //String jti = payload.getString(SecurityConstants.JWT_JTI); // JWT唯一标识
        Long expireTime = payload.getLong(SecurityConstants.JWT_EXP); // JWT过期时间戳(单位：秒)
        String username = payload.getString("user_name");
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                //删除token
                LambdaQueryWrapper<RefreshTokenEntity> queryWrapper = new QueryWrapper<RefreshTokenEntity>().lambda()
                        .eq(RefreshTokenEntity::getUsename, username);
                refreshTokenService.remove(queryWrapper);
                log.info("登录退出，假设实现了token注销");
            }
        } else { // token 永不过期则永久加入黑名单
            log.info("登录退出，假设实现了token注销");
        }
        return ResponseData.success("注销成功");
    }

    /**
     * 获取请用户信息
     * @return
     */
    @GetMapping("/getInfo")
    public ResponseData loginInfo() {
        UserEntity userEntity = userService.loadUserByUsername(null);
        LoginInfoVO loginInfoVO = new LoginInfoVO();
        loginInfoVO.setName(userEntity.getUsername());
        loginInfoVO.setRoles(new ArrayList<>(Arrays.asList(userEntity.getRoles().split(","))));
        return ResponseData.success(loginInfoVO);
    }

    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    public ResponseData checkLogin(@RequestParam("userName") String userName){
       return ResponseData.success(refreshTokenService.checkLogin(userName));
    }


/*
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response){
        userService.refreshToken(request,response);
    }
*/
}
