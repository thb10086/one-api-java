package com.tang.core.modules.api.event;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ErrorMessageEvent extends ApplicationEvent {

    private PlatformApiKeysDto apiKeys;
    private TransferApiKeys transferApiKeys;
    private String message;
    private String userName;
    public ErrorMessageEvent(Object source, PlatformApiKeysDto apiKeys,TransferApiKeys transferApiKeys,String message,String userName) {
        super(source);
        this.apiKeys=apiKeys;
        this.message=message;
        this.transferApiKeys=transferApiKeys;
        this.userName = userName;
    }
}
