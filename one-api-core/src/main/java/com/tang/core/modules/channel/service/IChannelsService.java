package com.tang.core.modules.channel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.channel.model.Channels;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.channel.model.dto.ChannelsDto;
import com.tang.core.modules.channel.model.dto.ChannelsReqDto;
import com.tang.core.modules.channel.model.dto.ChannelsResponseDto;

import java.util.List;

/**
 * <p>
 * 渠道表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
public interface IChannelsService extends IService<Channels> {
    /**
     * 创建渠道
     * @param channelsDto
     * @return
     */
    Boolean createChannel(ChannelsDto channelsDto);

    /**
     * 修改渠道
     * @return
     */
    Boolean updateChannel();

    /**
     * 删除渠道
     * @return
     */
    Boolean deleteChannel();

    /**
     * 查询渠道
     * @param channelsReqDto
     * @return
     */
    Page<ChannelsResponseDto> queryChannel(ChannelsReqDto channelsReqDto);

    /**
     * 根据渠道ids获取渠道信息
     * @param channelIds
     * @return
     */
    List<Channels> queryChannelList(List<Long> channelIds);

}
