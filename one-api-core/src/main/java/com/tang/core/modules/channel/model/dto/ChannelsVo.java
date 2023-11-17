package com.tang.core.modules.channel.model.dto;

import com.tang.core.modules.models.model.Models;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ChannelsVo {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private Integer channelType;

    /**
     * 渠道所属的分组ID
     */
    private List<Long> groupIds;

    /**
     * 模型ID
     */
    private List<Long> models;

    /**
     * 代理地址
     */
    private String proxyAddress;

    /**
     * api-keys
     */
    private List<PlatformApiKeysDto> apiKeys;


}
