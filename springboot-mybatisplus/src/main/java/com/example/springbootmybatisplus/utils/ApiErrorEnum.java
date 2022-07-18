package com.example.springbootmybatisplus.utils;

public enum ApiErrorEnum {

    CHECK_DATABASE_WRONG("CHECK_DATABASE_WRONG","get data from database wrong",0001);

    ApiErrorEnum(String errorName,String errorMsg,int errorCode){
        this.errorName = errorName;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String errorName;
    public String errorMsg;
    public int errorCode;

    }
