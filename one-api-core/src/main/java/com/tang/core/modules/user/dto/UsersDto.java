package com.tang.core.modules.user.dto;

import com.tang.core.modules.groups.model.dto.GroupsDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


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

    private String avatar;

    private String email;

    /**
     * 额度限制
     */
    private BigDecimal quotaLimit;

    /**
     * 已使用额度
     */
    private BigDecimal quotaUsed;

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
    private BigDecimal quotaRemaining;

    /**
     * 用户类型
     */
    private Integer userType;

    private String userTypeDesc;

    /**
     * 用户所属的分组ID
     */
    private List<GroupsDto> groupList;


}
