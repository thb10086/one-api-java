package com.tang.core.config;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.core.modules.models.model.Models;
import com.tang.core.modules.models.service.IModelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class RedisLoadConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    IModelsService iModelsService;

    @Autowired
    RedisService redisService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent  event) {
        log.info("------redis数据加载中------");
        List cacheList = redisService.getCacheList(RedisConstants.CACHE_MODELS);
        if (CollectionUtil.isEmpty(cacheList)){
           List<Models> list = iModelsService.list();
            log.info(list.toString());
            redisService.setCacheList(RedisConstants.CACHE_MODELS,list);
        }

        Object object = redisService.getCacheObject(RedisConstants.CACHE_MODELS_DEFAULT);
        if (object==null){
            Models models = iModelsService.getOne(new LambdaQueryWrapper<Models>().eq(Models::getModelName, Constants.DEFAULT_MODEL).eq(BaseEntity::getDelFlag, false));
            if (models==null){
                log.error("获取默认模型失败");
            }
            redisService.setCacheObject(RedisConstants.CACHE_MODELS_DEFAULT,models);
        }
    }
}
