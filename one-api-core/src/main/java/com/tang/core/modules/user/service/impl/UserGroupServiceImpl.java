package com.tang.core.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tang.core.modules.user.model.UserGroup;
import com.tang.core.modules.user.mapper.UserGroupMapper;
import com.tang.core.modules.user.service.IUserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements IUserGroupService {

    @Override
    public List<UserGroup> queryUserGroup(Long userId) {
        return list(new LambdaUpdateWrapper<UserGroup>().eq(UserGroup::getUserId,userId));
    }
}
