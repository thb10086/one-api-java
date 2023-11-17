package com.tang.common.enums;

public enum ChannelTypeEnums {
    OPEN_AI(0,"OpenAI"),AZURE(5,"Azure");
    ChannelTypeEnums(Integer code, String type){
        this.code=code;
        this.type=type;
    }
    private Integer code;
    private String type;

    public static Boolean byI(Integer code){
        for (ChannelTypeEnums type : ChannelTypeEnums.values()) {
            if (code.equals(type.code)){
                return true;
            }
        }
        return false;
    }

    public static String getType(Integer code){
        for (ChannelTypeEnums type : ChannelTypeEnums.values()) {
            if (code.equals(type.code)){
                return type.type;
            }
        }
        return "默认渠道";
    }
}
