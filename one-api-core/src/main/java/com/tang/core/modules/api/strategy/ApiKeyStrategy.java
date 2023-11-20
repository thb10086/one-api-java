package com.tang.core.modules.api.strategy;

import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;

import java.util.List;

public interface ApiKeyStrategy {
    PlatformApiKeysDto getApiKey(List<PlatformApiKeysDto>list);
}
