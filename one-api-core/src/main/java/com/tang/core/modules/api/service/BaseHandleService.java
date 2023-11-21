package com.tang.core.modules.api.service;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface BaseHandleService {
    /**
     *
     * @param chatCompletion
     * @param channels
     * @param apiKey
     * @return
     */
    Object completions(ChatCompletion chatCompletion, ChannelsVo channels, String apiKey);
}
