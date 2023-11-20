package com.tang.core.modules.api.strategy;

import com.tang.common.annotation.StrategyType;
import com.tang.common.enums.StrategyEnums;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@StrategyType(value = StrategyEnums.WEIGHT)
public class WeightStrategy implements ApiKeyStrategy{
    @Override
    public PlatformApiKeysDto getApiKey(List<PlatformApiKeysDto> apiKeys) {
        double totalWeight = 0;
        // 计算总权重值
        for (PlatformApiKeysDto apiKey : apiKeys) {
            if (!apiKey.getIsDisabled()) {
                totalWeight += apiKey.getWeight();
            }
        }
        if (totalWeight==0){
            Random random = new Random();
            int i = random.nextInt(apiKeys.size());
            return apiKeys.get(i);
        }

        // 根据权重值进行选择
        double random = Math.random() * totalWeight;
        double cumulativeWeight = 0;

        for (PlatformApiKeysDto apiKey : apiKeys) {
            if (!apiKey.getIsDisabled()) {
                cumulativeWeight += apiKey.getWeight();
                if (random < cumulativeWeight) {
                    return apiKey;
                }
            }
        }
        return null;
    }
}
