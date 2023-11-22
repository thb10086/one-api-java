package com.tang.core.modules.transfer.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.common.exception.ServiceException;
import com.tang.common.utils.ApiKeyGenerator;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.channel.model.Channels;
import com.tang.core.modules.channel.service.IChannelsService;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.tang.core.modules.transfer.mapper.TransferApiKeysMapper;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysCreateDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysReqDto;
import com.tang.core.modules.transfer.service.ITransferApiKeysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 中转平台API密钥表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class TransferApiKeysServiceImpl extends ServiceImpl<TransferApiKeysMapper, TransferApiKeys> implements ITransferApiKeysService {

    @Autowired
    RedisService redisService;

    @Autowired
    IChannelsService iChannelsService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean createTransferApiKey(TransferApiKeysCreateDto dto) {
        if (!checkChannelId(dto.getChannelId())){
            throw new ServiceException("渠道id不存在",500);
        }
        TransferApiKeys apiKeys = new TransferApiKeys();
        apiKeys.setApiKey(ApiKeyGenerator.nextApiKey());
        apiKeys.setChannelId(dto.getChannelId());
        apiKeys.setUserId(StpUtil.getLoginIdAsLong());
        apiKeys.setExpTime(dto.getExpTime());
        apiKeys.setKeyName(dto.getKeyName());
        apiKeys.setIsDisabled(false);
        apiKeys.setRequestLimit(dto.getRequestLimit());
        apiKeys.setQuotaUsed(BigDecimal.ZERO);
        apiKeys.setQuotaRemaining(dto.getQuota());
        return save(apiKeys);
    }

    Boolean checkChannelId(Long channelId){
        Object obj = redisService.getCacheObject(RedisConstants.CACHE_CHANNEL + channelId);
        if (Objects.isNull(obj)){
            Channels channels = iChannelsService.getById(channelId);
            if (channels==null){
                return false;
            }
        }
        return true;
    }

    @Override
    public Page<TransferApiKeysDto> queryTransferApiKeys(TransferApiKeysReqDto dto) {
        // 创建查询条件
        LambdaQueryWrapper<TransferApiKeys> queryWrapper = new LambdaQueryWrapper<TransferApiKeys>()
                // 查询条件：用户ID等于当前登录用户ID
                .eq(TransferApiKeys::getUserId, StpUtil.getLoginId())
                // 查询条件：如果dto中的keyName不为空，那么在数据库中模糊查询keyName
                .like(StringUtils.hasText(dto.getKeyName()), TransferApiKeys::getKeyName, dto.getKeyName())
                // 查询条件：删除标记为false，即未被删除的数据
                .eq(BaseEntity::getDelFlag, false)
                // 按创建时间降序排序
                .orderByDesc(BaseEntity::getCreateTime);

        // 执行分页查询
        Page<TransferApiKeys> page = page(dto.startPage(), queryWrapper);
        // 获取查询结果
        List<TransferApiKeys> records = page.getRecords();

        // 从查询结果中提取所有的channelId
        List<Long> channelIds = records.stream()
                .map(TransferApiKeys::getChannelId)
                .collect(Collectors.toList());

        // 查询channel信息，并以Map形式存储，便于后续根据channelId快速查找
        Map<Long, Channels> channelsMap = iChannelsService.queryChannelList(channelIds).stream()
                .collect(Collectors.toMap(Channels::getChannelId, Function.identity()));

        // 创建一个空的列表用于存储转换后的dto对象
        List<TransferApiKeysDto> dtoList = new ArrayList<>();
        // 遍历查询结果，将每个TransferApiKeys对象转换为TransferApiKeysDto对象
        for (TransferApiKeys record : records) {
            // 进行转换
            TransferApiKeysDto convert = BeanUtils.convert(record, TransferApiKeysDto.class);
            // 从channelsMap中根据channelId获取channel信息
            Channels channel = channelsMap.get(record.getChannelId());
            // 如果channel信息不为空，设置channelName
            if (channel != null) {
                convert.setChannelName(channel.getChannelName());
            }
            // 将转换后的对象添加到列表中
            dtoList.add(convert);
        }

        // 创建一个新的分页对象，用于返回转换后的dto对象
        Page<TransferApiKeysDto> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        // 设置转换后的dto对象列表
        resultPage.setRecords(dtoList);
        // 返回分页对象
        return resultPage;
    }


    @Override
    public Boolean deleteTransferApiKeys(Long apiKeyId) {
        return removeById(apiKeyId);
    }

    @Override
    public String getApiKey(Long apiKeyId) {
        TransferApiKeys apiKeys = getById(apiKeyId);
        return apiKeys.getApiKey();
    }

    @Override
    public TransferApiKeys getTransferApiKeysByKey(String apiKey) {
        LambdaQueryWrapper<TransferApiKeys> queryWrapper = new LambdaQueryWrapper<TransferApiKeys>().eq(TransferApiKeys::getApiKey, apiKey);
        return getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean updateQuota(Long apiKeyId, BigDecimal quota) {
        LambdaUpdateWrapper<TransferApiKeys> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .setSql("quota_used = quota_used + "+quota)
                .setSql("quota_remaining = quota_remaining - " + quota)
                .eq(TransferApiKeys::getTransferKeyId, apiKeyId);
        update(updateWrapper);

        UpdateWrapper<TransferApiKeys> disableWrapper = new UpdateWrapper<>();
        disableWrapper
                .set("is_disabled", 1)
                .eq("transfer_key_id", apiKeyId)
                .apply("quota_used <= 0");
        update(disableWrapper);
        return true;
    }


}
