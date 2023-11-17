package com.tang.core.config;

import com.tang.common.annotation.ChannelType;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.core.modules.api.service.BaseHandleService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChannelTypeContent implements InitializingBean {
    @Autowired
    public List<BaseHandleService> handleServices;

    public static Map<ChannelTypeEnums,BaseHandleService> CONTENT = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (BaseHandleService service : handleServices) {
            ChannelType channelType = service.getClass().getAnnotation(ChannelType.class);
            if (channelType!=null){
                CONTENT.put(channelType.value(),service);
            }
        }
    }
}
