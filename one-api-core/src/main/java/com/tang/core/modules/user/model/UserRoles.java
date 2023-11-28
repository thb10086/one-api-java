package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-28
 */
@Getter
@Setter
@TableName("user_roles")
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;


}
