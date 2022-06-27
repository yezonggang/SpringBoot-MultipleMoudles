package com.example.springbootdemo.model.reponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public  class BaseResponse {
    @JsonProperty("code")
    int code;
    @JsonProperty("data")
    Object data;

    public BaseResponse(int code, Object objectData) {
        this.code = code;
        this.data = objectData;
    }

    public static  BaseResponse success(Object data){
        return new BaseResponse(20000,data);
    }


}
