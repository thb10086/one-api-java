package com.tang.core.modules.channel.model.dto;

import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ChannelsResponseDto {


    private Long channelId;
    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private Integer channelType;

    /**
     * 渠道类型
     */
    private String channelTypeDesc;
    /**
     * 渠道分组
     */
    private String groups;
    /**
     * api-key比例
     */
    private String rate;


}
