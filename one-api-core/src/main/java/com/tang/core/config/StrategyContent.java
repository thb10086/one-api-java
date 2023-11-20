package com.tang.core.config;

import com.tang.common.annotation.StrategyType;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.StrategyEnums;
import com.tang.core.modules.api.service.BaseHandleService;
import com.tang.core.modules.api.strategy.ApiKeyStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class StrategyContent implements InitializingBean {
    @Autowired
    public List<ApiKeyStrategy> strategyList;
    public static Map<StrategyEnums, ApiKeyStrategy> CONTENT = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (ApiKeyStrategy strategy : strategyList) {
            StrategyType type = strategy.getClass().getAnnotation(StrategyType.class);
            if (type!=null){
                CONTENT.put(type.value(),strategy);
            }
        }
    }
}
