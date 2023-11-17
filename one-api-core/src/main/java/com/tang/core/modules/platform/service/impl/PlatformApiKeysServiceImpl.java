package com.tang.core.modules.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.domain.BaseEntity;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.platform.model.PlatformApiKeys;
import com.tang.core.modules.platform.mapper.PlatformApiKeysMapper;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import com.tang.core.modules.platform.service.IPlatformApiKeysService;
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
public class PlatformApiKeysServiceImpl extends ServiceImpl<PlatformApiKeysMapper, PlatformApiKeys> implements IPlatformApiKeysService {

    @Override
    public List<PlatformApiKeysDto> getPlatformApiKeysByChannelId(Long channelId) {
        LambdaQueryWrapper<PlatformApiKeys> queryWrapper = new LambdaQueryWrapper<PlatformApiKeys>()
                .eq(PlatformApiKeys::getChannelId, channelId)
                .eq(BaseEntity::getCreateUserId, StpUtil.getLoginId())
                .eq(BaseEntity::getDelFlag, false)
                .orderByDesc(BaseEntity::getCreateTime);
        return BeanUtils.convert(list(queryWrapper),PlatformApiKeysDto.class);
    }

    @Override
    public List<PlatformApiKeysDto> getPlatformApiKeysByChannelIds(List<Long> channelIds) {
        LambdaQueryWrapper<PlatformApiKeys> queryWrapper = new LambdaQueryWrapper<PlatformApiKeys>()
                .in(PlatformApiKeys::getChannelId, channelIds)
                .eq(BaseEntity::getCreateUserId, StpUtil.getLoginId())
                .eq(BaseEntity::getDelFlag, false)
                .orderByDesc(BaseEntity::getCreateTime);
        return BeanUtils.convert(list(queryWrapper),PlatformApiKeysDto.class);
    }

    @Override
    public Boolean batchTestApiKey(Long channelId) {
        return null;
    }

    @Override
    public Boolean testApiKey(Long apiKeyId) {
        return null;
    }

    @Override
    public Boolean createPlatformApiKeys(PlatformApiKeysDto platformApiKeys) {
        return save(BeanUtils.convert(platformApiKeys, PlatformApiKeys.class));
    }



    @Override
    public Boolean createPlatformApiKeysList(List<PlatformApiKeysDto> platformApiKeysList) {
        return saveBatch(BeanUtils.convert(platformApiKeysList, PlatformApiKeys.class));
    }

    @Override
    public Boolean deletePlatformApiKeys(Long apiKeyId) {
        return removeById(apiKeyId);
    }

    @Override
    public Boolean deletePlatformApiKeysByChannelId(Long channelId) {
        return remove(new LambdaQueryWrapper<PlatformApiKeys>().eq(PlatformApiKeys::getChannelId,channelId));
    }
}
