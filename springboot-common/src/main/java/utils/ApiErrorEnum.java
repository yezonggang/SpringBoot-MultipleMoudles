package utils;

public enum ApiErrorEnum {

    CHECK_DATABASE_WRONG("CHECK_DATABASE_WRONG","get data from database wrong","0001");

    ApiErrorEnum(String errorName,String errorMsg,String errorCode){
        this.errorName = errorName;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String errorName;
    public String errorMsg;
    public String errorCode;

    }
