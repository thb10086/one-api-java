package com.tang.common.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段注入
 * @author caochengbo
 * @date 2023/6/13
 */
@Component
@Slf4j
public class MybatisplusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isTraceEnabled()) {
            log.trace("MybatisplusMetaObjectHandler...insertFill... {} ",
                    metaObject.getOriginalObject().getClass().getName());
        }

        // 获取登录登录人信息
        setFieldValByName("createUserId", StpUtil.getLoginIdAsLong(), metaObject);
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("delFlag", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update");
    }

}
