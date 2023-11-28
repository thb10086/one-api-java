package com.tang.core.modules.logs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.api.event.ErrorMessageEvent;
import com.tang.core.modules.api.event.MessageEvent;
import com.tang.core.modules.logs.model.RequestLogs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.logs.model.dto.RequestLogsDto;
import com.tang.core.modules.logs.model.dto.RequestLogsReqDto;
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
    void saveLog(MessageEvent event);

    @EventListener(classes = ErrorMessageEvent.class)
    @Async
    void saveErrorLog(ErrorMessageEvent event);

    Page<RequestLogsDto> pageList(RequestLogsReqDto reqDto);

}
