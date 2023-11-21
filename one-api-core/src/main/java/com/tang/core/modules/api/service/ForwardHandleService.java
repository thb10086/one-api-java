package com.tang.core.modules.api.service;

import com.alibaba.fastjson.JSON;
import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.OpenAIErrorEnums;
import com.tang.common.exception.ServiceException;
import com.tang.common.utils.StringUtils;
import com.tang.core.config.ChannelTypeContent;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.channel.service.IChannelsService;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.transfer.service.ITransferApiKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class ForwardHandleService {

    @Autowired
    private ITransferApiKeysService iTransferApiKeysService;

    @Autowired
    private IChannelsService iChannelsService;

    @Autowired
    private RedisService redisService;

    public Object completions(ChatCompletion chatCompletion, String authorization){
        if (authorization==null){
            throw new ServiceException(OpenAIErrorEnums.ERROR_301);
        }
        String apiKey = authorization.replace("Bearer ", "");
        //中转平台的key对象
        TransferApiKeys transferApiKey = iTransferApiKeysService.getTransferApiKeysByKey(apiKey);
        if (Objects.isNull(transferApiKey)){
            throw new ServiceException(OpenAIErrorEnums.ERROR_303);
        }
        if (transferApiKey.getIsDisabled()){
            throw new ServiceException(OpenAIErrorEnums.ERROR_302);
        }
        if (transferApiKey.getQuotaRemaining().compareTo(transferApiKey.getQuotaUsed())<=0){
            throw new ServiceException(OpenAIErrorEnums.ERROR_305);
        }
        if (transferApiKey.getRequestCount().intValue()>=transferApiKey.getRequestLimit().intValue()){
            throw new ServiceException(OpenAIErrorEnums.ERROR_308);
        }
        ChannelsVo channel;
        //渠道信息
        String jsonObject = redisService.getCacheObject(RedisConstants.CACHE_CHANNEL + transferApiKey.getChannelId());
        if (StringUtils.isNotEmpty(jsonObject)){
            //直接从缓存拿渠道数据
            channel = JSON.parseObject(jsonObject, ChannelsVo.class);
        }else {
            //从数据拿渠道数据
            channel = iChannelsService.queryChannelsById(transferApiKey.getChannelId());
            //重新缓存信息
            redisService.setCacheObject(RedisConstants.CACHE_CHANNEL+transferApiKey.getChannelId(),channel);
        }
        BaseHandleService service = ChannelTypeContent.CONTENT.get(ChannelTypeEnums.get(channel.getChannelType()));
        //传入请求参数，渠道信息，跟平台中转api-key
        return service.completions(chatCompletion,channel,apiKey);
    }

}
