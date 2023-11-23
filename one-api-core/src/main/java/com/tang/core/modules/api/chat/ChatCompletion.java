package com.tang.core.modules.api.chat;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.tang.core.modules.api.utils.TikTokensUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * 描述： chat模型参数
 *
 * @author https:www.unfbx.com
 * 2023-03-02
 */
@Data
@SuperBuilder
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletion extends BaseChatCompletion implements Serializable {

    /**
     * 问题描述
     */
    @NonNull
    private List<Message> messages;

    /**
     * 获取当前参数的tokens数
     */
    public long tokens() {
        if (CollectionUtil.isEmpty(this.messages) || StrUtil.isBlank(this.getModel())) {
            log.warn("参数异常model：{}，prompt：{}", this.getModel(), this.messages);
            return 0;
        }
        return TikTokensUtil.tokens(this.getModel(), this.messages);
    }

    public static ChatCompletion getDefaultChatCompletion(String model){
        Message message = new Message();
        message.setRole("user");
        message.setContent("回复我一个1");
        ChatCompletion completion = new ChatCompletion();
        completion.setModel(model);
        completion.setMessages(Lists.newArrayList(message));
        completion.setTemperature(0.5);
        completion.setPresencePenalty(0);
        completion.setFrequencyPenalty(0);
        return completion;
    }
}
