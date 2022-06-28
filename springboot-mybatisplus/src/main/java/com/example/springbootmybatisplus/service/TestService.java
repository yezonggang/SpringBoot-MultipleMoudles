package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.TestPojo;

public class TestService {
    TestPojo test = new TestPojo("1", "yzg");
    String a = test.getId();
}
