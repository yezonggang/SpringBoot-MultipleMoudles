package com.example.springbootmybatisplus.utils;


import lombok.Data;

@Data
public class ApiError {
    private  String errorName;
    private  String errorMsg;
    private  String errorCode;

    public ApiError(String errorName, String errorMsg, String errorCode) {
        this.errorName = errorName;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public static ApiError from(String errorName, String errorMsg, String errorCode) {
        return new ApiError(errorName,errorMsg,errorCode);
    }

    public static ApiError from(ApiErrorEnum apiErrorEnum){
        return new ApiError(apiErrorEnum.errorName, apiErrorEnum.errorMsg, apiErrorEnum.errorCode);
    }

}
