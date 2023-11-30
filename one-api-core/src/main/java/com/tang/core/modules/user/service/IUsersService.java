package com.tang.core.modules.user.service;

import com.tang.core.modules.user.dto.UsersDto;
import com.tang.core.modules.user.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-10
 */
public interface IUsersService extends IService<Users> {
    public UsersDto getUserInfo(Long userId);
}
