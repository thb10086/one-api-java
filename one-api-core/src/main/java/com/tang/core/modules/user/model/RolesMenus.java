package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-28
 */
@Getter
@Setter
@TableName("roles_menus")
public class RolesMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menusId;


}
