package com.example.springcloudLogin.controller;

import com.alibaba.fastjson.JSONObject;
import constant.SecurityConstants;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import response.ResponseData;
import utils.JwtUtils;

@Slf4j
@RestController
public class LoginController {

    @ApiOperation(value = "注销")
    @DeleteMapping("/logout")
    public ResponseData logout() {
        JSONObject payload = JwtUtils.getJwtPayload();
        String jti = payload.getString(SecurityConstants.JWT_JTI); // JWT唯一标识
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
