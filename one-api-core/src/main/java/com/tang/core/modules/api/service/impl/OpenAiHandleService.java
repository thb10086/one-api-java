package com.tang.core.modules.api.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.tang.common.annotation.ChannelType;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.StrategyEnums;
import com.tang.common.utils.BeanUtils;
import com.tang.common.utils.StringUtils;
import com.tang.core.config.StrategyContent;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.listener.OpenAiListener;
import com.tang.core.modules.api.request.BaseApiRequest;
import com.tang.core.modules.api.request.DefaultApiRequest;
import com.tang.core.modules.api.request.StreamApiRequest;
import com.tang.core.modules.api.request.params.DefaultRequestParams;
import com.tang.core.modules.api.request.params.StreamRequestParams;
import com.tang.core.modules.api.service.BaseHandleService;
import com.tang.core.modules.api.strategy.ApiKeyStrategy;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.models.service.IModelsService;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.user.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
@ChannelType(ChannelTypeEnums.OPEN_AI)
public class OpenAiHandleService implements BaseHandleService {

    @Autowired
    RedisService redisService;

    @Autowired
    StreamApiRequest apiRequest;

    @Autowired
    IModelsService iModelsService;

    @Autowired
    DefaultApiRequest defaultApiRequest;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Override
    public Object completions(ChatCompletion chatCompletion, ChannelsVo channels, TransferApiKeys transferApiKeys) {
        // 获取渠道中的api keys
        List<PlatformApiKeysDto> apiKeys = channels.getApiKeys();

        // 获取策略模式
        ApiKeyStrategy strategy = StrategyContent.CONTENT.get(StrategyEnums.get(channels.getStrategy()));

        // 使用策略模式获取请求所需的api key
        PlatformApiKeysDto requestApiKey = strategy.getApiKey(apiKeys);

        // 判断代理地址是否为空，为空则设置为默认值
        if (StringUtils.isEmpty(channels.getProxyAddress())){
            channels.setProxyAddress(Constants.DEFAULT_API_HOST);
        }

        // 设置请求的URL
        String url = channels.getProxyAddress() + Constants.DEFAULT_API_URL;



        // 判断是否要进行流式请求
        if (chatCompletion.getStream()){
            // 创建SseEmitter
            SseEmitter sseEmitter = new SseEmitter();
            // 创建并设置监听器
            OpenAiListener listener = new OpenAiListener(sseEmitter,channels,requestApiKey,chatCompletion,transferApiKeys,eventPublisher);

            // 创建并设置流式请求参数
            StreamRequestParams params = new StreamRequestParams();
            params.setUrl(url);
            params.setChatCompletion(chatCompletion);
            params.setApiKey(requestApiKey.getApiKey());
            params.setListener(listener);

            // 发起请求
            apiRequest.request(params);

            return sseEmitter;
        }else {
            // 创建并设置默认请求参数
            DefaultRequestParams params = new DefaultRequestParams();
            params.setUrl(url);
            params.setChatCompletion(chatCompletion);
            params.setApiKey(requestApiKey.getApiKey());
            // 发起请求
            return defaultApiRequest.request(params);
        }

    }
}
