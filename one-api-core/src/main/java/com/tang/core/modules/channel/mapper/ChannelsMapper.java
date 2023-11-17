package com.tang.core.modules.channel.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.channel.model.Channels;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.core.modules.channel.model.dto.ChannelsResponseDto;

/**
 * <p>
 * 渠道表 Mapper 接口
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
public interface ChannelsMapper extends BaseMapper<Channels> {
    //先用java写。效果可能好点
//    Page<ChannelsResponseDto> queryChannelByUserGroup(Long userId);
}
