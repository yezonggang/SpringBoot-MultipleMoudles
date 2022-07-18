package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSONException;
import com.example.springbootdemo.model.entitiy.user.User;
import com.example.springbootdemo.model.reponse.BaseResponse;
import com.example.springbootdemo.service.vue.RoleServiceImpl;
import com.example.springbootdemo.service.vue.TokenService;
import com.example.springbootdemo.service.vue.UserServiceImpl;
import com.example.springbootdemo.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/vue-element-admin/user")
public class VueUserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TokenService tokenService;

	@Autowired
	RoleServiceImpl roleService;
	// 登录
	@ApiOperation(value = "登录接口", notes = "登录接口")
	@PostMapping("/login")
	@ResponseBody
	public BaseResponse login(HttpServletResponse response,
                              @RequestBody User inputUser) throws JSONException {
		User userForBase = new User();
		inputUser.getUsername();
		userForBase.setId(userService.findByUsername(inputUser).getId());
		userForBase.setUsername(userService.findByUsername(inputUser).getUsername());
		userForBase.setPasswd(userService.findByUsername(inputUser).getPasswd());
		if (!userForBase.getPasswd().equals(inputUser.getPasswd())) {
			String info="登录失败,密码错误";
			return BaseResponse.success(info);
		} else {
			String token = tokenService.getToken(userForBase);
			String info=token;
			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			response.addCookie(cookie);
			return BaseResponse.success(info);
		}
	}


	// 验证
	@ApiOperation(value = "info", notes = "info")
	@GetMapping("/info")
	@ResponseBody
	public BaseResponse getInfo(){
		return BaseResponse.success(roleService.getRoleFromToken(""));
	}

	//logout
	@ApiOperation(value = "logout", notes = "logout")
	@PostMapping("/logout")
	@ResponseBody
	public BaseResponse logout(){
		return BaseResponse.success("退出");
	}

	@ApiOperation(value = "获取信息", notes = "获取信息")
	@GetMapping("/checkNow")
	public String getMessage() {
		// 取出token中带的用户id 进行操作
		System.out.println(TokenUtils.getTokenUserId());

		return "您已通过验证";
	}


}

