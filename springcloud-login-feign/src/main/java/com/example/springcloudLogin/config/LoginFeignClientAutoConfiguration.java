package com.example.springcloudLogin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.example.springcloudLogin.feign")
@ComponentScan("com.example.springcloudLogin")
public class LoginFeignClientAutoConfiguration {


}
