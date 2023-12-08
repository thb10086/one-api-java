package com.tang.core.modules.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.user.dto.UsersDto;
import com.tang.core.modules.user.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-10
 */
public interface IUsersService extends IService<Users> {
    UsersDto getUserInfo(Long userId);
    void deductionAmount(Long userId, BigDecimal amount);
    Boolean createUser(UsersDto usersDto);
//    Page<U>
}
