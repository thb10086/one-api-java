package com.tang.core.modules.transfer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysCreateDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysReqDto;

import java.math.BigDecimal;

/**
 * <p>
 * 中转平台API密钥表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface ITransferApiKeysService extends IService<TransferApiKeys> {
    /**
     * 创建中转api-key
     * @param dto
     * @return
     */
    Boolean createTransferApiKey(TransferApiKeysCreateDto dto);

    /**
     * 查询中转api-key
     * @param dto
     * @return
     */
    Page<TransferApiKeysDto> queryTransferApiKeys(TransferApiKeysReqDto dto);

    /**
     * 删除中转api-key
     * @param apiKeyId
     * @return
     */
    Boolean deleteTransferApiKeys(Long apiKeyId);

    /**
     * 根据id查询api-key
     * @param apiKeyId
     * @return
     */
    String getApiKey(Long apiKeyId);

    /**
     * 通过api-key去查询
     * @param apiKey
     * @return
     */
    TransferApiKeys getTransferApiKeysByKey(String apiKey);

    /**
     * 更新额度
     * @param apiKeyId
     * @param quota
     * @return
     */
    Boolean updateQuota(Long apiKeyId, BigDecimal quota);
}
