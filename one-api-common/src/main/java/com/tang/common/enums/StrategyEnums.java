package com.tang.common.enums;

public enum StrategyEnums {
    WEIGHT(0,"权重");
    StrategyEnums(Integer code, String type){
        this.code=code;
        this.type=type;
    }
    private Integer code;
    private String type;

    public static Boolean byI(Integer code){
        for (StrategyEnums type : StrategyEnums.values()) {
            if (code.equals(type.code)){
                return true;
            }
        }
        return false;
    }

    public static StrategyEnums get(Integer code){
        for (StrategyEnums type : StrategyEnums.values()) {
            if (code.equals(type.code)){
                return type;
            }
        }
        return StrategyEnums.WEIGHT;
    }

    public static String getType(Integer code){
        for (StrategyEnums type : StrategyEnums.values()) {
            if (code.equals(type.code)){
                return type.type;
            }
        }
        return "";
    }
}
