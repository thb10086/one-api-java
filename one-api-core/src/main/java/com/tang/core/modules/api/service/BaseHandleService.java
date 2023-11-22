package com.tang.core.modules.api.service;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface BaseHandleService {
    /**
     *
     * @param chatCompletion
     * @param channels
     * @param apiKeys
     * @return
     */
    Object completions(ChatCompletion chatCompletion, ChannelsVo channels, TransferApiKeys apiKeys);
}
