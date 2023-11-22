package com.tang.core.modules.logs.service.impl;

import cn.dev33.satoken.temp.SaTempUtil;
import com.google.common.collect.Lists;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.api.chat.Message;
import com.tang.core.modules.api.event.MessageEvent;
import com.tang.core.modules.api.utils.TikTokensUtil;
import com.tang.core.modules.logs.model.RequestLogs;
import com.tang.core.modules.logs.mapper.RequestLogsMapper;
import com.tang.core.modules.logs.model.dto.RequestLogsEvent;
import com.tang.core.modules.logs.service.IRequestLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.models.service.IModelsService;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.transfer.service.ITransferApiKeysService;
import com.tang.core.modules.user.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 请求日志表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class RequestLogsServiceImpl extends ServiceImpl<RequestLogsMapper, RequestLogs> implements IRequestLogsService {

    @Autowired
    ITransferApiKeysService iTransferApiKeysService;

    @Autowired
    IModelsService iModelsService;

    @Override
    public void saveLog(MessageEvent event) {
        //响应对象
        ChatCompletionResponse response = event.getResponse();
        //输入对象
        ChatCompletion completion = event.getCompletion();
        //模型信息
        ModelsDto model = iModelsService.getModelByName(completion.getModel());
        RequestLogs logs = new RequestLogs();
        //输出tokens计算
        logs.setOutputTokens(TikTokensUtil.tokens(response.getModel(), Lists.newArrayList(response.getChoices().get(0).getMessage())));
        //输入tokens计算
        logs.setInputTokens(TikTokensUtil.tokens(completion.getModel(),completion.getMessages()));
        //设置apikey
        logs.setApiKeyId(event.getApiKeys().getTransferKeyId());
        //用户名称
        logs.setUserName(event.getUserName());
        //密钥的名称
        logs.setKeyName(event.getApiKeys().getKeyName());
        //输入金额
        BigDecimal inputMoney = model.getInputMoney().multiply(new BigDecimal(logs.getInputTokens()).divide(new BigDecimal("1000"),BigDecimal.ROUND_HALF_UP,3));
        //输出金额
        BigDecimal outputMoney = model.getOutputMoney().multiply(new BigDecimal(logs.getOutputTokens()).divide(new BigDecimal("1000"),BigDecimal.ROUND_HALF_UP,3));
        logs.setQuotaConsumed(inputMoney.add(outputMoney));
        save(logs);
        //去扣这个key的额度
        iTransferApiKeysService.updateQuota(event.getApiKeys().getTransferKeyId(),logs.getQuotaConsumed());
    }


}
