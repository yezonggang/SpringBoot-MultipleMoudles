package com.example.springbootdemo.model.reponse;

public class ApiError {
    private static String errorname;
    private static String ID;

    public String getErrorname() {
        return errorname;
    }

    public String getID() {
        return ID;
    }

    public ApiError(String errorname, String ID) {
        this.errorname = errorname;
        this.ID = ID;
    }

    public static ApiError from(String errorname, String ID) {
        return new ApiError(errorname,ID);
    }
}
