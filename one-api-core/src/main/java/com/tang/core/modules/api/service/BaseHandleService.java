package com.tang.core.modules.api.service;

import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface BaseHandleService {
    SseEmitter completions(ChatCompletion chatCompletion, ChannelsVo channels, String apiKey);
}
