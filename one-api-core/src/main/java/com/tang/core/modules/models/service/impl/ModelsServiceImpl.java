package com.tang.core.modules.models.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tang.common.config.RedisService;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.models.model.Models;
import com.tang.core.modules.models.mapper.ModelsMapper;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.models.service.IModelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@Service
public class ModelsServiceImpl extends ServiceImpl<ModelsMapper, Models> implements IModelsService {


    @Autowired
    RedisService redisService;

    @Override
    public List<ModelsDto> getModels() {
        List<Models> list = redisService.getCacheList(RedisConstants.CACHE_MODELS);
        if (CollectionUtil.isEmpty(list)){
            List<Models> modelsList = list(new LambdaQueryWrapper<Models>().eq(Models::getDelFlag, false));
            redisService.setCacheList(RedisConstants.CACHE_MODELS,modelsList);
            return BeanUtils.convert(modelsList,ModelsDto.class);
        }
        return BeanUtils.convert(list,ModelsDto.class);
    }

    @Override
    public Boolean checkModels(String models) {
        try {
            List<Long> modelIds = Arrays.stream(models.split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            Set<Long> modelsDtoList = getModels().stream()
                    .map(ModelsDto::getModelId)
                    .collect(Collectors.toSet());

            return modelIds.stream().allMatch(modelsDtoList::contains);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("输入的模型ID包含非法字符，无法转换为Long类型", e);
        }
    }

    @Override
    public ModelsDto getModelByName(String name) {
        List<Models> list = redisService.getCacheList(RedisConstants.CACHE_MODELS);
        if (CollectionUtil.isEmpty(list)){
            LambdaQueryWrapper<Models> queryWrapper = new LambdaQueryWrapper<Models>().eq(Models::getModelName, name).eq(BaseEntity::getDelFlag, false);
            Models models = getBaseMapper().selectOne(queryWrapper);
            if (models==null){
                models = redisService.getCacheObject(RedisConstants.CACHE_MODELS_DEFAULT);
            }
            return BeanUtils.convert(models,ModelsDto.class);
        }
        List<Models> modelsList = list.stream().filter(i -> i.getModelName().equals(name)).collect(Collectors.toList());

        Models models = modelsList.get(0);
        if (models==null){
            models = redisService.getCacheObject(RedisConstants.CACHE_MODELS_DEFAULT);
        }
        return BeanUtils.convert(models,ModelsDto.class);
    }


}
