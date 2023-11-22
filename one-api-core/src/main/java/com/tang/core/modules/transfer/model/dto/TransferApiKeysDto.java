package com.tang.core.modules.transfer.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
@TableName("transfer_api_keys")
public class TransferApiKeysDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "transfer_key_id", type = IdType.AUTO)
    private Long transferKeyId;

    /**
     * 秘钥名称
     */
    private String keyName;

    /**
     * 关联的用户ID
     */
    private Long userId;

    /**
     * 关联的渠道ID
     */
    private Long channelId;

    /**
     * 渠道的名称
     */
    private String channelName;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 请求次数
     */
    private Integer requestCount;


    /**
     * 请求次数限制
     */
    private Integer requestLimit;

    /**
     * 已使用额度
     */
    private BigDecimal quotaUsed;

    /**
     * 到期时间
     */
    private LocalDateTime expTime;

    /**
     * 剩余额度
     */
    private BigDecimal quotaRemaining;

}
