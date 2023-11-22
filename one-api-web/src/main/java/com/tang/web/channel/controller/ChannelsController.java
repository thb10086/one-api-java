package com.tang.web.channel.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.domain.R;
import com.tang.core.modules.channel.model.dto.ChannelsDto;
import com.tang.core.modules.channel.model.dto.ChannelsReqDto;
import com.tang.core.modules.channel.model.dto.ChannelsResponseDto;
import com.tang.core.modules.channel.service.IChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @Autowired
    private Validator validator;

    @PostMapping("/channel/create")
    public R<Boolean> createChannels( MultipartHttpServletRequest request){
        MultipartFile multipartFile = request.getFile("file");
        String parameter = request.getParameter("dto");
        ChannelsDto dto = JSON.parseObject(parameter, ChannelsDto.class);
        BindingResult result = new BeanPropertyBindingResult(dto, "dto");
        validator.validate(dto, result);
        dto.setCsvFile(multipartFile);
        return R.ok(iChannelsService.createChannel(dto));
    }

    @GetMapping("/channel/queryChannel")
    public R<Page<ChannelsResponseDto>> queryChannel(ChannelsReqDto reqDto){
        return R.ok(iChannelsService.queryChannel(reqDto));
    }

}

