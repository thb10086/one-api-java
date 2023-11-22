package com.tang.core.modules.api.request;


import com.alibaba.fastjson.JSON;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.api.request.params.DefaultRequestParams;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DefaultApiRequest extends BaseApiRequest{
    public ChatCompletionResponse request(DefaultRequestParams params){
        Request request = new Request.Builder().url(params.getUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+params.getApiKey())
                .post(RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(params.getChatCompletion())))
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return JSON.parseObject(response.body().string(), ChatCompletionResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ChatCompletionResponse();
    }
}
