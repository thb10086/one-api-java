package com.tang.core.modules.user.service.impl;

import com.tang.core.modules.user.model.Users;
import com.tang.core.modules.user.mapper.UsersMapper;
import com.tang.core.modules.user.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
