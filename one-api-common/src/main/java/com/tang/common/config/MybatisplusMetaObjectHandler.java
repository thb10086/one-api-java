package com.tang.common.config;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tang.common.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

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
        Object extra = null;
        try {
            extra = StpUtil.getExtra("user");
        }catch (Exception e){

        }finally {
            if (Objects.nonNull(extra)){
                // 获取登录登录人信息
                JSONObject user = JSON.parseObject(JSON.toJSONString(extra));
                setFieldValByName("createUserId", Long.valueOf(user.get("userId").toString()), metaObject);
                setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                setFieldValByName("delFlag", false, metaObject);
                setFieldValByName("createUserName", user.get("username"), metaObject);
            }else {
                setFieldValByName("createUserId", 1L, metaObject);
                setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                setFieldValByName("delFlag", false, metaObject);
                setFieldValByName("createUserName", "admin", metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update");
    }

}
