package com.example.springcloudLogin.feign;

import client.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import response.ResponseData;

/**
 * 通过服务名调用，需使用ribbon
 */
@Component
@FeignClient(value = "login-service",configuration = FeignClientConfiguration.class)
public interface LoginFeignClient {
    @RequestMapping(value = "/auth/user/checkToken", method = RequestMethod.GET,produces = "application/json")
    ResponseData checkLogin(@RequestParam("userName")String userName);
}
