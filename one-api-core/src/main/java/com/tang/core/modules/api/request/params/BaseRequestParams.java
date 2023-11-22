package com.tang.core.modules.api.request.params;

import com.tang.core.modules.api.chat.ChatCompletion;
import lombok.Data;

@Data
public class BaseRequestParams {
    private ChatCompletion chatCompletion;
    private String url;
    private String apiKey;
}
