package com.example.springbootdemo.controller;


import com.example.springbootdemo.model.entitiy.Student;
import com.example.springbootdemo.service.StudentNewService;
import com.example.springbootdemo.service.TeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Api("测试swagger的api")
@RequestMapping("/One")
public class TestController {
    final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    TeacherServiceImpl teacherimpl;

    @Autowired
    StudentNewService studentNewService;
    //直接获取模拟数据
    @GetMapping("/getStudent")
    @ApiOperation(value = "查询学生", notes = "xzxxxx")

    public List<Student> getStudent() throws ExecutionException, InterruptedException {
        logger.info("xxxxxxxxxxx");
        return teacherimpl.getStudents().get();
    }

    // 用mybaits-plus从数据库中取数据
    @GetMapping("/getStudentByMybatisPlus")
    @ApiOperation(value = "查询学生", notes = "xzxxxx")

    public String getStudentByID() {
        return studentNewService.getItemTest();
    }


}