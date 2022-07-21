package com.example.springbootmybatisplus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
/* 如果configurationproperties的类上使用了component注解就不用
@EnableConfigurationProperties({JsonWebTokenProperty.class})*/
@SpringBootApplication()
@MapperScan("com.example.springbootmybatisplus.mapper")
public class SpringbootMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusApplication.class, args);
    }



}
