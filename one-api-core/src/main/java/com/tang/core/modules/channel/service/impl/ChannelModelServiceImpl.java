package com.tang.core.modules.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.core.modules.channel.model.ChannelModel;
import com.tang.core.modules.channel.mapper.ChannelModelMapper;
import com.tang.core.modules.channel.service.IChannelModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 渠道关联模型表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class ChannelModelServiceImpl extends ServiceImpl<ChannelModelMapper, ChannelModel> implements IChannelModelService {

    @Override
    public List<ChannelModel> getChannelModelByChannelId(Long channelId) {
        return list(new LambdaQueryWrapper<ChannelModel>().eq(ChannelModel::getChannelId, channelId));
    }

    @Override
    public Boolean createChannelModelList(List<ChannelModel> channelModels) {
        return saveBatch(channelModels);
    }

    @Override
    public Boolean createChannelModel(ChannelModel channelModel) {
        return save(channelModel);
    }

    @Override
    public Boolean deleteChannelModel(Long channelId) {
        return remove(new LambdaQueryWrapper<ChannelModel>().eq(ChannelModel::getChannelId,channelId));
    }
}
