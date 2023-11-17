package com.tang.core.modules.groups.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class GroupsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long groupId;

    /**
     * 分组名称
     */
    private String groupName;

}
