package com.tang.core.config;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.core.modules.config.model.SysConfig;
import com.tang.core.modules.config.service.ISysConfigService;
import com.tang.core.modules.models.model.Models;
import com.tang.core.modules.models.service.IModelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RedisLoadConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    IModelsService iModelsService;

    @Autowired
    RedisService redisService;

    @Autowired
    ISysConfigService iSysConfigService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent  event) {
        log.info("------redis数据加载中------");
        List<SysConfig> list = iSysConfigService.list(new LambdaQueryWrapper<SysConfig>().eq(BaseEntity::getDelFlag, false));
        Map<String, String> configMap = list.stream().collect(Collectors.toMap(SysConfig::getSysCode, SysConfig::getSysValue));
        redisService.setCacheMap(RedisConstants.CACHE_SYS_CONFIG,configMap);
    }
}
