package com.tang.core.modules.api.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tang.core.modules.api.chat.ChatChoice;
import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.api.chat.Message;
import com.tang.core.modules.api.common.Choice;
import com.tang.core.modules.api.event.MessageEvent;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.user.model.Users;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
public class OpenAiListener extends EventSourceListener {

    private SseEmitter sseEmitter;
    private String userName;
    private ChatCompletion chatCompletion;
    private StringBuffer buffer = new StringBuffer();
    private String role;
    private ChatCompletionResponse chatCompletionResponse;
    private String finishReason;
    private ApplicationEventPublisher eventPublisher;
    private TransferApiKeys apiKeys;
    public OpenAiListener(SseEmitter sseEmitter, String userName, ChatCompletion chatCompletion, TransferApiKeys apiKeys, ApplicationEventPublisher eventPublisher){
        this.sseEmitter = sseEmitter;
        this.chatCompletion=chatCompletion;
        this.userName=userName;
        this.apiKeys=apiKeys;
        this.eventPublisher=eventPublisher;
    }
    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        ChatChoice choice = new ChatChoice();
        Message message = new Message();
        message.setRole(role);
        message.setContent(buffer.toString());
        choice.setIndex(0);
        choice.setMessage(message);
        choice.setFinishReason(finishReason);
        chatCompletionResponse.setChoices(Lists.newArrayList(choice));
        //发送消息给日志
        eventPublisher.publishEvent(new MessageEvent(this,chatCompletionResponse,chatCompletion,userName,apiKeys));
        sseEmitter.complete();
        log.info("关闭连接");
        chatCompletionResponse = null;
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
        try {
            if ("[DONE]".equals(data)) {
                log.info("OpenAI返回数据结束了");
                return;
            }
            ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
            handleData(response);
            sseEmitter.send(SseEmitter.event()
                    .id(id)
                    .data(response)
                    .reconnectTime(3000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleData(ChatCompletionResponse response) {
        if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
            ChatChoice choice = response.getChoices().get(0);
            if (choice != null) {
                Message delta = choice.getDelta();
                if (delta != null && delta.getRole() != null) {
                    // 这是一个定义角色的事件
                    role = delta.getRole();
                    //设置基础的信息
                    chatCompletionResponse.setId(response.getId());
                    chatCompletionResponse.setModel(response.getModel());
                    chatCompletionResponse.setCreated(response.getCreated());
                    chatCompletionResponse.setObject(response.getObject());
                } else if (choice.getFinishReason() != null) {
                    finishReason = choice.getFinishReason();
                } else if (delta != null && delta.getContent() != null) {
                    String content = delta.getContent();
                    buffer.append(content);
                }
            }
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
        chatCompletionResponse = new ChatCompletionResponse();
        log.info("连接成功");
    }
}
