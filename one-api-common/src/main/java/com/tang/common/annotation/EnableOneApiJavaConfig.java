package com.tang.common.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan(basePackages = "com.tang.**")
@MapperScan(basePackages = "com.tang.**.**.mapper")
@EnableWebMvc
@EnableAsync
public @interface EnableOneApiJavaConfig {
}
