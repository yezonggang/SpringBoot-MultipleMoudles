package com.example.springbootmybatisplus.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseData {

    public ResponseData(String reponseinfo, Object data) {
    }

    public static String getReponseinfo() {
        return reponseinfo;
    }

    public static Object data;
    public static String reponseinfo;
    public static ApiError apiError;

    public static ResponseData from(ApiError apiError){
        return new ResponseData(apiError.getErrorCode(), apiError.getErrorName());
    }

    public static ResponseData success(Object data){
        return new ResponseData("success",data);
    }
}
