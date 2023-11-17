package com.tang.core.config;

import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
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
        if (Objects.nonNull(cacheList)){
           List<Models> list = iModelsService.list();
            log.info(list.toString());
            redisService.setCacheList(RedisConstants.CACHE_MODELS,list);
        }
    }
}
