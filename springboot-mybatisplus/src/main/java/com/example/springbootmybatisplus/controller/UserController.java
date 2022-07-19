package com.example.springbootmybatisplus.controller;

import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import com.example.springbootmybatisplus.utils.AuthenticationFacade;
import com.example.springbootmybatisplus.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzg
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private AuthenticationFacade authenticationFacade;


    @GetMapping("/getInfo")
    public ResponseData loginInfo(HttpServletRequest request) throws IOException {
        logger.info("begin to llogger login_info");
        return userService.loginInfo(request);
    }


/*
    @PostMapping("/user/check_login")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.checkLogin(request,response);
    }
*/


/*
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response){
        userService.refreshToken(request,response);
    }
*/

}
