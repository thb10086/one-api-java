package com.tang.common.annotation;

import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.StrategyEnums;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrategyType {
    StrategyEnums value();
}
