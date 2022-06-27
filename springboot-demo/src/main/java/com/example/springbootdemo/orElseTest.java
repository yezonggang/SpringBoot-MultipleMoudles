package com.example.springbootdemo;

import java.util.Optional;

public class orElseTest {
    public static void main(String[] args){
        String xxxxx = null;
        String op1 = Optional.ofNullable(xxxxx).orElse("ye");
        System.out.println(op1);
        String op2= Optional.ofNullable(xxxxx).orElseGet(() -> "zong");
        System.out.println(op2);
        String op3 = Optional.ofNullable("111").orElse("gang");
        System.out.println(op3);
        String op4 = Optional.ofNullable("222").orElseGet(() -> "hao");
        System.out.println(op4);

    }
}
