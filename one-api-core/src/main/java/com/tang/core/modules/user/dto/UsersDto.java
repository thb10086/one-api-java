package com.tang.core.modules.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-10
 */
@Data
public class UsersDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer userId;

    private String username;

    /**
     * 额度限制
     */
    private Integer quotaLimit;

    /**
     * 已使用额度
     */
    private Integer quotaUsed;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 请求次数
     */
    private Integer requestCount;

    /**
     * 剩余额度
     */
    private Integer quotaRemaining;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户所属的分组ID
     */
    private Integer groupId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String tokenName;

    private String tokenValue;

}
