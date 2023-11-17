package com.tang.core.modules.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.core.modules.channel.model.ChannelGroup;
import com.tang.core.modules.channel.mapper.ChannelGroupMapper;
import com.tang.core.modules.channel.service.IChannelGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class ChannelGroupServiceImpl extends ServiceImpl<ChannelGroupMapper, ChannelGroup> implements IChannelGroupService {

    @Override
    public List<ChannelGroup> getChannelGroupByChannelId(Long channelId) {
        return list(new LambdaQueryWrapper<ChannelGroup>().eq(ChannelGroup::getChannelId,channelId));
    }

    @Override
    public List<ChannelGroup> getChannelGroupByGroupId(List<Long> groupIds) {
        return list(new LambdaQueryWrapper<ChannelGroup>().in(ChannelGroup::getGroupId,groupIds));
    }

    @Override
    public Boolean createChannelGroup(ChannelGroup channelGroup) {
        return save(channelGroup);
    }

    @Override
    public Boolean createChannelGroupList(List<ChannelGroup> channelGroupList) {
        return saveBatch(channelGroupList);
    }

    @Override
    public Boolean deleteChannelGroup(Long channelId) {
        return remove(new LambdaQueryWrapper<ChannelGroup>().eq(ChannelGroup::getChannelId,channelId));
    }
}
