package com.tang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.tang.**")
@MapperScan(basePackages = "com.tang.**.**.mapper")
@EnableWebMvc
public class OneApiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneApiWebApplication.class);
    }
}
