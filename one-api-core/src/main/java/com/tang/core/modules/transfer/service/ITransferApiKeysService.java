package com.tang.core.modules.transfer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.transfer.model.TransferApiKeys;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysCreateDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysReqDto;

/**
 * <p>
 * 中转平台API密钥表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface ITransferApiKeysService extends IService<TransferApiKeys> {
    Boolean createTransferApiKey(TransferApiKeysCreateDto dto);
    Page<TransferApiKeysDto> queryTransferApiKeys(TransferApiKeysReqDto dto);
    Boolean deleteTransferApiKeys(Long apiKeyId);
    String getApiKey(Long apiKeyId);
    TransferApiKeys getTransferApiKeysByKey(String apiKey);
}
