package com.tang.common.annotation;

import com.tang.common.enums.ChannelTypeEnums;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChannelType {
    ChannelTypeEnums value() default ChannelTypeEnums.OPEN_AI;
}
