package com.example.springbootmybatisplus.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class ResponseData implements Serializable {
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";
    private final static String WARN = "warn";

    private  String code;
    private  String msg ;
    private  Object data;

    public ResponseData(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData success(Object data){
        return new ResponseData("200","success",data);
    }

    public static ResponseData fail(ApiError apiError){
        return new ResponseData(apiError.getErrorCode(),apiError.getErrorMsg(),apiError.getErrorName());
    }
}
