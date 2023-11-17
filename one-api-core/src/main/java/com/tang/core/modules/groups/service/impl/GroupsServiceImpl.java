package com.tang.core.modules.groups.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.groups.model.Groups;
import com.tang.core.modules.groups.mapper.GroupsMapper;
import com.tang.core.modules.groups.model.dto.GroupsDto;
import com.tang.core.modules.groups.service.IGroupsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户分组表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements IGroupsService {

    @Override
    public List<GroupsDto> getGroups() {
        LambdaQueryWrapper<Groups> queryWrapper = new LambdaQueryWrapper<Groups>()
                .eq(Groups::getCreateUserId, StpUtil.getLoginId())
                .eq(Groups::getDelFlag, false);
        List<Groups> list = list(queryWrapper);
        return BeanUtils.convert(list,GroupsDto.class);
    }

    @Override
    public Boolean checkGroups(String groups) {
        try {
            List<Long> modelIds = Arrays.stream(groups.split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            Set<Long> modelsDtoList = getGroups().stream()
                    .map(GroupsDto::getGroupId)
                    .collect(Collectors.toSet());

            return modelIds.stream().allMatch(modelsDtoList::contains);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("输入的模型ID包含非法字符，无法转换为Long类型", e);
        }
    }
}
