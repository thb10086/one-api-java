package com.tang.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.domain.R;
import com.tang.core.modules.logs.model.dto.RequestLogsDto;
import com.tang.core.modules.logs.model.dto.RequestLogsReqDto;
import com.tang.core.modules.logs.service.IRequestLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 请求日志表 前端控制器
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@RestController
@RequestMapping("/logs")
public class RequestLogsController {

    @Autowired
    private IRequestLogsService iRequestLogsService;


    @GetMapping("/pageList")
    public R<Page<RequestLogsDto>> pageList(RequestLogsReqDto reqDto){
        return R.ok(iRequestLogsService.pageList(reqDto));
    }
}

