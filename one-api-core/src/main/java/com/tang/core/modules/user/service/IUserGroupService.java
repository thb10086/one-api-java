package com.tang.core.modules.user.service;

import com.tang.core.modules.user.model.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
public interface IUserGroupService extends IService<UserGroup> {

    /**
     * 查询用户的分组
     * @param userId
     * @return
     */
    List<UserGroup> queryUserGroup(Long userId);
}
