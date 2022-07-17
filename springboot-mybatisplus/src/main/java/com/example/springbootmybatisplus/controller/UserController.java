package com.example.springbootmybatisplus;


import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import com.example.springbootmybatisplus.utils.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

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
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping("/user/check_login")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.checkLogin(request,response);
    }


    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response){
        userService.refreshToken(request,response);
    }

}
