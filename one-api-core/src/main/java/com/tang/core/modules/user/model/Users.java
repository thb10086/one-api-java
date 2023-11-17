package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tang.common.domain.BaseEntity;
import lombok.Data;


import java.io.Serializable;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-10
 */
@Data
public class Users extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String password;

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


}
