package com.tang.core.modules.logs.service.impl;

import com.tang.core.modules.logs.model.RequestLogs;
import com.tang.core.modules.logs.mapper.RequestLogsMapper;
import com.tang.core.modules.logs.service.IRequestLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请求日志表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class RequestLogsServiceImpl extends ServiceImpl<RequestLogsMapper, RequestLogs> implements IRequestLogsService {

}
