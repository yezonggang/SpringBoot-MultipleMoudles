package com.example.springbootdemo.model.reponse;

public class ResponseData {
    public ResponseData() {
    }

    public ResponseData(String reponseinfo, Object data) {
    }

    public static String getReponseinfo() {
        return reponseinfo;
    }

    public static Object data;
    public static String reponseinfo;
    public static ApiError apiError;

    public static ResponseData from(ApiError apiError){
        return new ResponseData(apiError.getID(),apiError.getErrorname());
    }

    public static ResponseData success(Object data){
        return new ResponseData("success",data);
    }
}
