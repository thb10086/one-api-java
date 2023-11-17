package com.tang.core.modules.user.service.impl;

import com.tang.core.modules.user.model.UserModel;
import com.tang.core.modules.user.mapper.UserModelMapper;
import com.tang.core.modules.user.service.IUserModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class UserModelServiceImpl extends ServiceImpl<UserModelMapper, UserModel> implements IUserModelService {

}
