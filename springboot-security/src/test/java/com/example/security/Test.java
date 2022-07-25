package com.example.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
 
    public static void main(String[] args) {
         
        String password = "admin@123";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
 
        //加密：bcryptPasswordEncoder进行密码加密
        String encodedPassword = bcryptPasswordEncoder.encode(password);
        System.out.println("bcryptPasswordEncoder进行密码加密:"+encodedPassword);
 
        //解密：
        boolean flag = bcryptPasswordEncoder.matches(password, encodedPassword);
        //如果flag为true，则解密成功  false，则解密失败
        System.out.println("解密："+flag);
 
    }
 
}