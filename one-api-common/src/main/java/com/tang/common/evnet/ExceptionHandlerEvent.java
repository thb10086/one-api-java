package com.tang.common.evnet;

import com.tang.common.exception.OpenAIRequestException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class ExceptionHandlerEvent extends ApplicationEvent {

    private OpenAIRequestException openAIRequestException;

    public ExceptionHandlerEvent(Object source,OpenAIRequestException openAIRequestException) {
        super(source);
        this.openAIRequestException=openAIRequestException;
    }
}
