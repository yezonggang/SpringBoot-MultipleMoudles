package com.example.springbootdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;

public class TestJson {
    public static void main(String[] args) {
        System.out.println("hello world");
        String test = "{\"id\":\"151421341dt\",\"name\":\"xsfdfd\"}";
        JSONObject jb = JSON.parseObject(test);
        String value = jb.getString("keys");



        System.out.println(String.format("schema_%s",test));

        //System.out.println(value);
    }
}
