package com.tang.core.modules.api.request;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApiRequest {
    @Autowired
    OkHttpClient okHttpClient;

    MediaType JSON_MEDIA_TYPE = MediaType.parse(ContentType.JSON.getValue());
    EventSource.Factory factory;

    @PostConstruct
    public void init() {
        factory = EventSources.createFactory(okHttpClient);
    }

    public void request(RequestParams params){
        Request request = new Request.Builder().url(params.getUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+params.getApiKey())
                .post(RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(params.getChatCompletion())))
                .build();
        factory.newEventSource(request,params.getListener());
    }
}
