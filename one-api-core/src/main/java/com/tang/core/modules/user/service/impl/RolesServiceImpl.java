package com.tang.core.modules.user.service.impl;

import com.tang.core.modules.user.model.Roles;
import com.tang.core.modules.user.mapper.RolesMapper;
import com.tang.core.modules.user.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-28
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

}
