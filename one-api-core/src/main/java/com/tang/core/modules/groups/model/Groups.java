package com.tang.core.modules.groups.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户分组表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
@TableName(value = "`groups`")
public class Groups extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.AUTO)
    private Long groupId;

    /**
     * 分组名称
     */
    private String groupName;

}
