package com.tang.core.modules.logs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class RequestLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 关联的API密钥ID
     */
    private Integer apiKeyId;

    /**
     * 请求时间
     */
    private LocalDateTime requestTime;

    /**
     * 输入的token数
     */
    private Integer inputTokens;

    /**
     * 输出的token数
     */
    private Integer outputTokens;

    /**
     * 使用的额度
     */
    private Integer quotaConsumed;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者用户ID
     */
    private Integer createUserId;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 版本号
     */
    private Integer version;


}
