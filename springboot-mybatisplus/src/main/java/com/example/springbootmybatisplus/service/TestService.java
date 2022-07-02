package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.TestPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestService {
    static Logger logger = LoggerFactory.getLogger(TestService.class);
    TestPojo test = new TestPojo("1", "yzg");
    String a = test.getId();
}
