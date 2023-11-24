package com.tang.core.modules.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.platform.model.PlatformApiKeys;
import com.tang.core.modules.platform.mapper.PlatformApiKeysMapper;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import com.tang.core.modules.platform.service.IPlatformApiKeysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    RedisService redisService;

    @Override
    public List<PlatformApiKeysDto> getPlatformApiKeysByChannelId(Long channelId,Long createUserId) {
        LambdaQueryWrapper<PlatformApiKeys> queryWrapper = new LambdaQueryWrapper<PlatformApiKeys>()
                .eq(PlatformApiKeys::getChannelId, channelId)
                .eq(BaseEntity::getCreateUserId, createUserId)
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
    public Boolean createPlatformApiKeys(PlatformApiKeysDto platformApiKeys) {
        return save(BeanUtils.convert(platformApiKeys, PlatformApiKeys.class));
    }



    @Override
    public Boolean createPlatformApiKeysList(List<PlatformApiKeysDto> platformApiKeysList) {
        List<PlatformApiKeys> convert = BeanUtils.convert(platformApiKeysList, PlatformApiKeys.class);
        boolean b = saveBatch(convert);
        for (int i = 0; i < convert.size(); i++) {
            platformApiKeysList.get(i).setPlatformKeyId(convert.get(i).getPlatformKeyId());
        }
        return b;
    }

    @Override
    public Boolean deletePlatformApiKeys(Long apiKeyId) {
        return removeById(apiKeyId);
    }

    @Override
    public Boolean deletePlatformApiKeysByChannelId(Long channelId) {
        return remove(new LambdaQueryWrapper<PlatformApiKeys>().eq(PlatformApiKeys::getChannelId,channelId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean updatePlatformApiKeys(PlatformApiKeysDto dto, ChannelsVo vo) {
        Map<Long, PlatformApiKeysDto> keysDtoMap = vo.getApiKeys().stream().collect(Collectors.toMap(PlatformApiKeysDto::getPlatformKeyId, Function.identity()));
        keysDtoMap.put(dto.getPlatformKeyId(),dto);
        List<PlatformApiKeysDto> newApiKeys = keysDtoMap.values().stream().collect(Collectors.toList());
        vo.setApiKeys(newApiKeys);
        redisService.setCacheObject(RedisConstants.CACHE_CHANNEL+dto.getChannelId(),vo);
        updateById(BeanUtils.convert(dto,PlatformApiKeys.class));
        return true;
    }
}
