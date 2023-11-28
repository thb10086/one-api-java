package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Menus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 路由地址
     */
    private String route;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 类型
     */
    private String type;

    /**
     * 权限标识
     */
    private String permission;

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
