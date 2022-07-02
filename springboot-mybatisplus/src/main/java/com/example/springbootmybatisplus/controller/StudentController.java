package com.example.springbootmybatisplus.controller;


import com.example.springbootmybatisplus.service.impl.StudentServiceImpl;
import com.example.springbootmybatisplus.utils.ApiError;
import com.example.springbootmybatisplus.utils.ApiErrorEnum;
import com.example.springbootmybatisplus.utils.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzg
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/student")
@Api("这是测试方法")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @GetMapping("/get")
    public ResponseData getAll(){
        try {
            return ResponseData.success(studentService.getAll());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResponseData.success(ApiError.from(ApiErrorEnum.CHECK_DATABASE_WRONG));
        }
    }
}
