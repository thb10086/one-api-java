package com.tang.core.modules.logs.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tang.common.domain.BaseEntity;
import com.tang.common.domain.BasePage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class RequestLogsReqDto extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 密钥名称
     */
    private String keyName;

    /**
     * 用户名称
     */

    private String userName;

    /**
     * 请求模型
     */
    private String requestModel;

    /**
     * 请求时间
     */
    private LocalDateTime requestTimeStart;

    /**
     * 请求时间
     */
    private LocalDateTime requestTimeEnd;

}
