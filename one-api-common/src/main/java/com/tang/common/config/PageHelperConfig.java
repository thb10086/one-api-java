package com.tang.common.config;
import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        // 设置数据库类型为mysql
        properties.setProperty("helperDialect","mysql");
        // 设置为true时，会将RowBounds第一个参数offset当做pageNum页码使用
        properties.setProperty("offsetAsPageNum","true");
        // 设置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount","true");
        // 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
        properties.setProperty("pageSizeZero","true");
        // 设置为true时，如果pageSize>0就进行分页查询；如果pageSize<=0就查询全部
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
