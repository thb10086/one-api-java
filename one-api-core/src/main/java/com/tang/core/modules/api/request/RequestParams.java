package com.tang.core.modules.api.request;

import com.tang.core.modules.api.chat.ChatCompletion;
import lombok.Data;
import okhttp3.sse.EventSourceListener;

@Data
public class RequestParams {
    private String url;
    private String apiKey;
    private ChatCompletion chatCompletion;
    private EventSourceListener listener;
}
