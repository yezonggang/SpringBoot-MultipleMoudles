package com.example.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatisplus.entity.UserEntity;
import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import com.example.springbootmybatisplus.config.security.AuthenticationFacade;
import com.example.springbootmybatisplus.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    public ResponseData loginInfo(HttpServletRequest request) {
        logger.info("begin to llogger login_info");
        return userService.loginInfo(request);
    }

    @GetMapping("/testPage")
    public ResponseData testPage(@RequestParam(value = "orderby") String userName) {
        logger.info("begin to test page"+userName);
        return ResponseData.success(userService.testPage(userName));
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
