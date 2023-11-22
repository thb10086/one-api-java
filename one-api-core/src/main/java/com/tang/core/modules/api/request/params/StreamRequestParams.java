package com.tang.core.modules.api.request.params;

import lombok.Data;
import okhttp3.sse.EventSourceListener;

@Data
public class StreamRequestParams extends BaseRequestParams{

    private EventSourceListener listener;
}
