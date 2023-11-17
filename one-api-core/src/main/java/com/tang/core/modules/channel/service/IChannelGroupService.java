package com.tang.core.modules.channel.service;

import com.tang.core.modules.channel.model.ChannelGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface IChannelGroupService extends IService<ChannelGroup> {

    /**
     * 根据渠道id查询渠道分组关系
     * @param channelId
     * @return
     */
    List<ChannelGroup> getChannelGroupByChannelId(Long channelId);

    /**
     * 根据渠道id查询渠道分组关系
     * @param groupId
     * @return
     */
    List<ChannelGroup> getChannelGroupByGroupId(List<Long> groupIds);

    /**
     * 创建渠道分组关系
     * @param channelGroup
     * @return
     */
    Boolean createChannelGroup(ChannelGroup channelGroup);

    /**
     * 批量创建渠道分组关系
     * @param channelGroupList
     * @return
     */
    Boolean createChannelGroupList(List<ChannelGroup> channelGroupList);

    /**
     * 根据渠道id删除关系
     * @param channelId
     * @return
     */
    Boolean deleteChannelGroup(Long channelId);

}
