package com.example.springcloudLogin.service;

import com.example.springcloudLogin.feign.LoginFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.ResponseData;

@Service
public class LoginClientServiceImpl implements ILoginClientService {
    @Autowired
    private LoginFeignClient loginFeignClient;

    @Override
    public ResponseData checkLogin(String userName) {
        return loginFeignClient.checkLogin(userName);
    }
}
