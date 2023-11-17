package com.tang.core.modules.platform.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
public class PlatformApiKeysDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long platformKeyId;

    /**
     * 关联的渠道ID
     */
    private Long channelId;

    /**
     * api-key（后续要加密处理）
     */
    private String apiKey;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 测试结果 0 未测试 1 成功 2 失败
     */
    private String testResult;

    /**
     * 响应时间
     */
    private Integer responseTime;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;


}
