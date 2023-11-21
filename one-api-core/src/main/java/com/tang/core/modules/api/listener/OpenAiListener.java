package com.tang.core.modules.api.listener;

import com.alibaba.fastjson.JSON;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
public class OpenAiListener extends EventSourceListener {

    private SseEmitter sseEmitter;

    public OpenAiListener(SseEmitter sseEmitter){
        this.sseEmitter = sseEmitter;

    }
    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        sseEmitter.complete();
        log.info("关闭连接");
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
        try {
            if ("[DONE]".equals(data)) {
                log.info("OpenAI返回数据结束了");
                return;
            }
            ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .data(response)
                    .reconnectTime(3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
        log.info("连接异常");
        log.info(response.message());
        sseEmitter.completeWithError(t);
        eventSource.cancel();
        t.printStackTrace();
    }

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        log.info("连接成功");
    }
}
