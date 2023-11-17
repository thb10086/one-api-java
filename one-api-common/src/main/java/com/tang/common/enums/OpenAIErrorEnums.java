package com.tang.common.enums;

public enum OpenAIErrorEnums {
    ERROR_301(301,"未提供令牌"),
    ERROR_302(302,"令牌格式被禁用"),
    ERROR_303(303,"令牌在系统中不存在"),
    ERROR_304(304,"令牌未激活"),
    ERROR_305(305,"令牌额度不足"),
    ERROR_306(306,"上游服务器错误"),
    ERROR_307(307,"渠道没有可用的apiKey");
    OpenAIErrorEnums(Integer code, String message){
        this.code=code;
        this.message=message;
    }
    private Integer code;
    private String message;
    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
