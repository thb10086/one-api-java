package com.tang.core.modules.api.event;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.api.chat.Message;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.user.model.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class MessageEvent extends ApplicationEvent {

    private ChatCompletion completion;
    private ChatCompletionResponse response;
    private Long userId;
    private TransferApiKeys apiKeys;

    public MessageEvent(Object source,ChatCompletionResponse response,ChatCompletion completion,Long userId,TransferApiKeys apiKeys) {
        super(source);
        this.apiKeys=apiKeys;
        this.completion=completion;
        this.userId = userId;
        this.response=response;
    }
}
