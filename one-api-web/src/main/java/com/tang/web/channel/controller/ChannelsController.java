package com.tang.web.channel.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.domain.R;
import com.tang.core.modules.channel.model.dto.ChannelsDto;
import com.tang.core.modules.channel.model.dto.ChannelsReqDto;
import com.tang.core.modules.channel.model.dto.ChannelsResponseDto;
import com.tang.core.modules.channel.service.IChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 渠道表 前端控制器
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@RestController
public class ChannelsController {

    @Autowired
    IChannelsService iChannelsService;

    @PostMapping("/channel/create")
    public R<Boolean> createChannels(@RequestBody @Validated ChannelsDto dto){
        return R.ok(iChannelsService.createChannel(dto));
    }

    @GetMapping("/channel/queryChannel")
    public R<Page<ChannelsResponseDto>> queryChannel(ChannelsReqDto reqDto){
        return R.ok(iChannelsService.queryChannel(reqDto));
    }

}

