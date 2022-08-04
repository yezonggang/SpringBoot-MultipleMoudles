package com.example.springbootNacos.holder;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootNacos.domain.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 获取登录用户信息
 * Created by macro on 2020/6/17.
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息

        UserDTO userDTO = null;
        try {
            JSONObject jsonObject = null;
            String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("payload");
            if (!ObjectUtils.isEmpty(payload)) {
                jsonObject = JSONObject.parseObject(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
            }

            userDTO = new UserDTO();
            // userDTO.setUsername(jsonObject.getString("userName"));
            userDTO.setId(jsonObject.getString("id"));
            //userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userDTO;
    }
}
