package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-28
 */
@Getter
@Setter
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建者用户名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者用户ID
     */
    private Long createUserId;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 版本号
     */
    private Integer version;


}
