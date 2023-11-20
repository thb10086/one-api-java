package com.tang.core.modules.api.service.impl;

import com.tang.common.annotation.ChannelType;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.StrategyEnums;
import com.tang.common.utils.StringUtils;
import com.tang.core.config.StrategyContent;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.listener.OpenAiListener;
import com.tang.core.modules.api.request.ApiRequest;
import com.tang.core.modules.api.request.RequestParams;
import com.tang.core.modules.api.service.BaseHandleService;
import com.tang.core.modules.api.strategy.ApiKeyStrategy;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
@ChannelType(ChannelTypeEnums.OPEN_AI)
public class OpenAiHandleService implements BaseHandleService {

    @Autowired
    RedisService redisService;

    @Autowired
    ApiRequest apiRequest;

    @Override
    public SseEmitter completions(ChatCompletion chatCompletion, ChannelsVo channels, String apiKey) {
        SseEmitter sseEmitter = new SseEmitter();
        //从渠道中获取apikeys
        List<PlatformApiKeysDto> apiKeys = channels.getApiKeys();
        //获取策略模式
        ApiKeyStrategy strategy = StrategyContent.CONTENT.get(StrategyEnums.get(channels.getStrategy()));
        //根据策略获取请求用的apikey
        PlatformApiKeysDto apiKey1 = strategy.getApiKey(apiKeys);
        OpenAiListener listener = new OpenAiListener(sseEmitter);
        RequestParams params = new RequestParams();
        if (StringUtils.isEmpty(channels.getProxyAddress())){
            channels.setProxyAddress(Constants.DEFAULT_API_HOST);
        }else {
            channels.setProxyAddress(channels.getProxyAddress());
        }
        params.setUrl(channels.getProxyAddress()+Constants.DEFAULT_API_URL);
        params.setChatCompletion(chatCompletion);
        params.setApiKey(apiKey1.getApiKey());
        params.setListener(listener);
        //请求服务器
        apiRequest.request(params);
        return sseEmitter;
    }
}
