package com.tang.core.modules.api.strategy;

import cn.hutool.core.collection.CollectionUtil;
import com.tang.common.annotation.StrategyType;
import com.tang.common.enums.OpenAIErrorEnums;
import com.tang.common.enums.StrategyEnums;
import com.tang.common.exception.OpenAIRequestException;
import com.tang.common.exception.ServiceException;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@StrategyType(value = StrategyEnums.WEIGHT)
public class WeightStrategy implements ApiKeyStrategy{
    @Override
    public PlatformApiKeysDto getApiKey(List<PlatformApiKeysDto> apiKeys) {
        List<PlatformApiKeysDto> dtoList = apiKeys.stream().filter(i -> !i.getIsDisabled()).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(dtoList)){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_307);
        }
        double totalWeight = 0;
        // 计算总权重值
        for (PlatformApiKeysDto apiKey : dtoList) {
            totalWeight += apiKey.getWeight();

        }
        if (totalWeight==0){
            Random random = new Random();
            int i = random.nextInt(dtoList.size());
            return dtoList.get(i);
        }

        // 根据权重值进行选择
        double random = Math.random() * totalWeight;
        double cumulativeWeight = 0;

        for (PlatformApiKeysDto apiKey : dtoList) {
            cumulativeWeight += apiKey.getWeight();
            if (random < cumulativeWeight) {
                return apiKey;
            }
        }
        return null;
    }
}
