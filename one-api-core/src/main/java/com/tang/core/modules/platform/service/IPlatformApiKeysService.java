package com.tang.core.modules.platform.service;

import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.platform.model.PlatformApiKeys;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface IPlatformApiKeysService extends IService<PlatformApiKeys> {

    /**
     * 根据渠道id去查询平台api-key
     * @param channelId
     * @return
     */
    List<PlatformApiKeysDto> getPlatformApiKeysByChannelId(Long channelId);


    /**
     * 根据渠道id去查询平台api-key
     * @param channelIds
     * @return
     */
    List<PlatformApiKeysDto> getPlatformApiKeysByChannelIds(List<Long> channelIds);

    /**
     * 批量测试apikey
     * @param channelId
     * @return
     */
    Boolean batchTestApiKey(Long channelId);

    /**
     * 根据apiKeyId测试
     * @param apiKeyId
     * @return
     */
    Boolean testApiKey(Long apiKeyId);

    /**
     * 创建平台apikey
     * @param platformApiKeys
     * @return
     */
    Boolean createPlatformApiKeys(PlatformApiKeysDto platformApiKeys);

    /**
     * 创建平台apikey
     * @param platformApiKeysList
     * @return
     */
    Boolean createPlatformApiKeysList(List<PlatformApiKeysDto> platformApiKeysList);

    /**
     * 根据apikeyId去删除
     * @param apiKeyId
     * @return
     */
    Boolean deletePlatformApiKeys(Long apiKeyId);

    /**
     * 根据渠道删除apikey
     * @param channelId
     * @return
     */
    Boolean deletePlatformApiKeysByChannelId(Long channelId);

    /**
     * 修改缓存跟数据库
     * @param dto
     * @return
     */
    Boolean updatePlatformApiKeys(PlatformApiKeysDto dto, ChannelsVo vo);

}
