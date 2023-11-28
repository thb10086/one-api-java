package com.tang.core.modules.user.service.impl;

import com.tang.core.modules.user.model.UserRoles;
import com.tang.core.modules.user.mapper.UserRolesMapper;
import com.tang.core.modules.user.service.IUserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-28
 */
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements IUserRolesService {

}
