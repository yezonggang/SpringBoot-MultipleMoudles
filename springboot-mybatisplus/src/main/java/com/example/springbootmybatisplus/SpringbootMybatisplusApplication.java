package com.example.springbootmybatisplus;


import com.example.springbootmybatisplus.config.security.JsonWebTokenProperty;
import com.example.springbootmybatisplus.config.security.JsonWebTokenUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.example.springbootmybatisplus.mapper")
//@EnableConfigurationProperties({JsonWebTokenProperty.class})
public class SpringbootMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusApplication.class, args);
    }



}
