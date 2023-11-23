package com.tang.core.modules.api.request;


import com.alibaba.fastjson.JSON;
import com.tang.common.enums.OpenAIErrorEnums;
import com.tang.common.exception.ServiceException;
import com.tang.core.modules.api.chat.ChatCompletionResponse;
import com.tang.core.modules.api.request.params.DefaultRequestParams;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DefaultApiRequest extends BaseApiRequest{
    public ChatCompletionResponse request(DefaultRequestParams params) {
        Request request = new Request.Builder().url(params.getUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+params.getApiKey())
                .post(RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(params.getChatCompletion())))
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            OpenAIErrorEnums errorEnum = OpenAIErrorEnums.byI(response.code());
            if (errorEnum != null) {
                throw new ServiceException(errorEnum);
            }
            ChatCompletionResponse chatCompletionResponse = JSON.parseObject(response.body().string(), ChatCompletionResponse.class);
            response.close();
            return chatCompletionResponse;
        } catch (IOException e) {
            throw new ServiceException(OpenAIErrorEnums.ERROR_306);
        }
    }
}
