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
    private String userName;
    private TransferApiKeys apiKeys;

    public MessageEvent(Object source,ChatCompletionResponse response,ChatCompletion completion,String userName,TransferApiKeys apiKeys) {
        super(source);
        this.apiKeys=apiKeys;
        this.userName=userName;
        this.completion=completion;
        this.response=response;
    }
}
