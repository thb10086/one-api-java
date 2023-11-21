package com.tang.core.modules.api.request;

import cn.hutool.http.ContentType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class BaseApiRequest {
    @Autowired
    OkHttpClient okHttpClient;

    MediaType JSON_MEDIA_TYPE = MediaType.parse(ContentType.JSON.getValue());
    EventSource.Factory factory;

    @PostConstruct
    public void init() {
        factory = EventSources.createFactory(okHttpClient);
    }

}
