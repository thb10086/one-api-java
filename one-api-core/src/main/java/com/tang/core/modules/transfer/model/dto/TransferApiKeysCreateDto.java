package com.tang.core.modules.transfer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tang.common.config.LocalDateTimeDeserializer;
import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 中转平台API密钥表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
public class TransferApiKeysCreateDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联的渠道ID
     */
    @NotNull(message = "关联的渠道不能为空")
    private Long channelId;

    /**
     * 秘钥名称
     */
    @NotNull(message = "密钥名称不能为空")
    private String keyName;

    @NotNull(message = "过期时间不能为空")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime expTime;

    @NotNull(message = "额度不能为空")
    private BigDecimal quota;

}
