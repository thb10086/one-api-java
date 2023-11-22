package com.tang;

import com.tang.common.annotation.EnableOneApiJavaConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableOneApiJavaConfig
public class OneApiWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneApiWebApplication.class);
    }
}
