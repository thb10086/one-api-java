package com.tang.core.modules.api.service;

import com.alibaba.fastjson.JSON;
import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.enums.OpenAIErrorEnums;
import com.tang.common.exception.OpenAIRequestException;
import com.tang.common.utils.StringUtils;
import com.tang.core.config.ChannelTypeContent;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.channel.service.IChannelsService;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.transfer.service.ITransferApiKeysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ForwardHandleService {

    @Autowired
    private ITransferApiKeysService iTransferApiKeysService;

    @Autowired
    private IChannelsService iChannelsService;

    public Object completions(ChatCompletion chatCompletion, String authorization){

        if (authorization==null){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_301);
        }
        String apiKey = authorization.replace("Bearer ", "");
        //中转平台的key对象
        long startTime1 = System.currentTimeMillis();
        TransferApiKeys transferApiKey = iTransferApiKeysService.getTransferApiKeysByKey(apiKey);
        long endTime1 = System.currentTimeMillis();
        log.info("查询key：中转耗时："+(endTime1-startTime1)+"ms");
        if (Objects.isNull(transferApiKey)){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_303);
        }
        if (transferApiKey.getIsDisabled()){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_302);
        }
        if (transferApiKey.getQuotaRemaining().compareTo(transferApiKey.getQuotaUsed())<=0){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_305);
        }
        if (transferApiKey.getRequestCount().intValue()>=transferApiKey.getRequestLimit().intValue()){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_308);
        }
        if (transferApiKey.getExpTime().compareTo(LocalDateTime.now())<=0){
            throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_309);
        }
        //获取渠道信息
        long startTime = System.currentTimeMillis();
        ChannelsVo channel = iChannelsService.queryChannelsById(transferApiKey.getChannelId());
        long endTime = System.currentTimeMillis();
        log.info("查询渠道：中转耗时："+(endTime-startTime)+"ms");
        //渠道信息
        BaseHandleService service = ChannelTypeContent.CONTENT.get(ChannelTypeEnums.get(channel.getChannelType()));
        //传入请求参数，渠道信息，跟平台中转api-key



        return service.completions(chatCompletion,channel,transferApiKey);
    }

}
