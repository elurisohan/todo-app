package com.tracknote.model;


public enum ErrorCodes {



    TASK_NOT_FOUND(1001),
    PROJECT_NOT_FOUND(1002),
    USER_NOT_FOUND(1003);

    private int code;


    ErrorCodes(int code){
        this.code=code;
    }

    public int getCode(){
        return this.code;
    }
}
