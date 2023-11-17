package com.tang.core.modules.channel.service;

import com.tang.core.modules.channel.model.ChannelModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 渠道关联模型表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface IChannelModelService extends IService<ChannelModel> {

    /**
     * 根据渠道id查询
     * @param channelId
     * @return
     */
    List<ChannelModel> getChannelModelByChannelId(Long channelId);

    /**
     * 批量创建渠道与模型之间的关系
     * @param channelModels
     * @return
     */
    Boolean createChannelModelList(List<ChannelModel> channelModels);

    /**
     * 创建渠道关联模型
     * @param channelModel
     * @return
     */
    Boolean createChannelModel(ChannelModel channelModel);

    /**
     * 根据渠道id批量删除
     * @param channelId
     * @return
     */
    Boolean deleteChannelModel(Long channelId);

}
