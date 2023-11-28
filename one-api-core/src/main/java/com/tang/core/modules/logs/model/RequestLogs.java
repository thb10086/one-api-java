package com.tang.core.modules.logs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 请求日志表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
@TableName("request_logs")
public class RequestLogs extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;


    /**
     * 密钥名称
     */
    private String keyName;

    /**
     * 用户名称
     */

    private String userName;

    /**
     * 关联的API密钥ID
     */
    private Long apiKeyId;

    /**
     * 请求时间
     */
    private LocalDateTime requestTime;

    /**
     * 输入的token数
     */
    private Integer inputTokens;

    /**
     * 请求状态
     */
    private Boolean requestStatus;

    /**
     * 请求的模型
     */
    private String requestModel;

    /**
     * 输出的token数
     */
    private Integer outputTokens;

    /**
     * 使用的额度
     */
    private BigDecimal quotaConsumed;

    private String remake;

}
