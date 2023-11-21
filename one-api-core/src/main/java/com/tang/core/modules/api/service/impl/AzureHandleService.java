package com.tang.core.modules.api.service.impl;

import com.tang.common.annotation.ChannelType;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.service.BaseHandleService;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@ChannelType(ChannelTypeEnums.AZURE)
public class AzureHandleService implements BaseHandleService {
    @Override
    public Object completions(ChatCompletion chatCompletion, ChannelsVo channels, String apiKey) {
        return null;
    }
}
