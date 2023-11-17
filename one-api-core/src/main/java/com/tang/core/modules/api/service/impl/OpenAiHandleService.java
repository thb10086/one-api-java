package com.tang.core.modules.api.service.impl;

import com.tang.core.modules.api.service.BaseHandleService;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@Service
public class OpenAiHandleService implements BaseHandleService {
    @Override
    public SseEmitter completions(ChatCompletion chatCompletion, ChannelsVo channels, String apiKey) {
        return null;
    }
}
