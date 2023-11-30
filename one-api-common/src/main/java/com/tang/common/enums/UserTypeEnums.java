package com.tang.common.enums;

import lombok.Data;

public enum UserTypeEnums {
    ORDINARY_USER(0,"普通用户"),ADMIN(1,"管理员"),SUPER_ADMIN(2,"超级管理员");
    UserTypeEnums(Integer code, String type){
        this.code=code;
        this.type=type;
    }
    private Integer code;
    private String type;

    public static Boolean byI(Integer code){
        for (UserTypeEnums type : UserTypeEnums.values()) {
            if (code.equals(type.code)){
                return true;
            }
        }
        return false;
    }

    public static UserTypeEnums get(Integer code){
        for (UserTypeEnums type : UserTypeEnums.values()) {
            if (code.equals(type.code)){
                return type;
            }
        }
        return UserTypeEnums.ORDINARY_USER;
    }

    public static String getType(Integer code){
        for (UserTypeEnums type : UserTypeEnums.values()) {
            if (code.equals(type.code)){
                return type.type;
            }
        }
        return "";
    }
}
