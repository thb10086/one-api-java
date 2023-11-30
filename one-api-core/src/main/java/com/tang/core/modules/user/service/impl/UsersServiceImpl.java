package com.tang.core.modules.user.service.impl;

import com.tang.common.enums.UserTypeEnums;
import com.tang.common.exception.ServiceException;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.groups.model.dto.GroupsDto;
import com.tang.core.modules.groups.service.IGroupsService;
import com.tang.core.modules.user.dto.UsersDto;
import com.tang.core.modules.user.model.Users;
import com.tang.core.modules.user.mapper.UsersMapper;
import com.tang.core.modules.user.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private IGroupsService iGroupsService;

    @Override
    public UsersDto getUserInfo(Long userId) {
        Users users = getById(userId);
        if (Objects.isNull(users)){
            throw new ServiceException("用户不存在！",500);
        }
        UsersDto convert = BeanUtils.convert(users, UsersDto.class);
        List<GroupsDto> groupsDtoList = iGroupsService.getUserGroups(userId);
        String userTypeDesc = UserTypeEnums.getType(convert.getUserType());
        convert.setGroupList(groupsDtoList);
        convert.setUserTypeDesc(userTypeDesc);
        return convert;
    }
}
