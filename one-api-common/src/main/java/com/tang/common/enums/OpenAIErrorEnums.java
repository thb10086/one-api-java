package com.tang.common.enums;

public enum OpenAIErrorEnums {
    ERROR_301(301,"未提供令牌"),
    ERROR_302(302,"令牌被禁用"),
    ERROR_303(303,"令牌在系统中不存在"),
    ERROR_304(304,"令牌未激活"),
    ERROR_305(305,"令牌额度不足"),
    ERROR_306(306,"上游服务器错误"),
    ERROR_307(307,"渠道没有可用的apiKey"),
    ERROR_308(308,"请求限额不足"),
    ERROR_309(309,"令牌已过期限"),
    MESSAGE_NOT_NUL(500, "Message 不能为空"),
    API_KEYS_NOT_NUL(500, "API KEYS 不能为空"),
    NO_ACTIVE_API_KEYS(500, "没有可用的API KEYS"),
    SYS_ERROR(500, "系统繁忙"),
    PARAM_ERROR(501, "参数异常"),
    RETRY_ERROR(502, "请求异常，请重试~"),
    //官方的错误码列表：https://platform.openai.com/docs/guides/error-codes/api-errors
    OPENAI_AUTHENTICATION_ERROR(401, "身份验证无效/提供的 API 密钥不正确/您必须是组织的成员才能使用 API"),
    OPENAI_LIMIT_ERROR(429 , "达到请求的速率限制/您超出了当前配额，请检查您的计划和帐单详细信息/发动机当前过载，请稍后重试"),
    OPENAI_SERVER_ERROR(500, "服务器在处理您的请求时出错");
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

    public static OpenAIErrorEnums byI(Integer code){
        for (OpenAIErrorEnums errorEnums : OpenAIErrorEnums.values()) {
            if (errorEnums.getCode().equals(code)){
                return errorEnums;
            }
        }
        return null;
    }
}
