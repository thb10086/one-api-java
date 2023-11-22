package com.tang.core.modules.logs.service;

import com.tang.core.modules.api.event.MessageEvent;
import com.tang.core.modules.logs.model.RequestLogs;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 * 请求日志表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface IRequestLogsService extends IService<RequestLogs> {

    @EventListener(classes = MessageEvent.class)
    @Async
    public void saveLog(MessageEvent event);

}
