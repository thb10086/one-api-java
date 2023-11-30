package com.tang.core.modules.groups.service;

import com.tang.core.modules.groups.model.Groups;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.groups.model.dto.GroupsDto;

import java.util.List;

/**
 * <p>
 * 用户分组表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
public interface IGroupsService extends IService<Groups> {

    List<GroupsDto> getUserGroups(Long userId);

    Boolean checkGroups(String groups);



}
