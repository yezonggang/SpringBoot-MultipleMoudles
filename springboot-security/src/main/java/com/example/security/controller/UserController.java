package com.example.security.controller;

import com.example.security.service.impl.UserServiceImpl;
import com.example.security.utils.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import response.ResponseData;

import javax.servlet.http.HttpServletRequest;

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
