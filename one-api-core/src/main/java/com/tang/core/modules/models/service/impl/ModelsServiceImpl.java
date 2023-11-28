package com.tang.core.modules.models.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.common.enums.OpenAIErrorEnums;
import com.tang.common.exception.OpenAIRequestException;
import com.tang.common.utils.BeanUtils;
import com.tang.common.utils.StringUtils;
import com.tang.core.modules.models.model.Models;
import com.tang.core.modules.models.mapper.ModelsMapper;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.models.model.dto.ModelsReqDto;
import com.tang.core.modules.models.service.IModelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.core.modules.user.model.UserModel;
import com.tang.core.modules.user.service.IUserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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

    @Autowired
    IUserModelService iUserModelService;

    @Override
    public List<ModelsDto> getMyModels() {
        LambdaQueryWrapper<Models> queryWrapper = new LambdaQueryWrapper<Models>().in(BaseEntity::getCreateUserId, 1L, StpUtil.getLoginIdAsLong()).eq(BaseEntity::getDelFlag, false);
        List<Models> modelsList = list(queryWrapper);
        return convert(modelsList,StpUtil.getLoginIdAsLong());
    }

    public Map<String,ModelsDto> cacheModelsDto(){
        Map<String, String> cacheMap = redisService.getCacheMap(RedisConstants.CACHE_SYS_CONFIG);
        String jsonModels = cacheMap.get(Constants.DEFAULT_MODEL_PRICE);
       return JSON.parseArray(jsonModels, ModelsDto.class).stream().collect(Collectors.toMap(ModelsDto::getModelName,Function.identity()));
    }

    public List<ModelsDto> convert(List<Models> modelsList,Long userId){
        Map<String, ModelsDto> cacheModelsDto = cacheModelsDto();
        List<Long> modelsIds = modelsList.stream().map(Models::getModelId).collect(Collectors.toList());
        List<UserModel> userModelList = iUserModelService.list(new LambdaQueryWrapper<UserModel>().in(UserModel::getModelId, modelsIds).eq(UserModel::getUserId, userId));
        Map<Long, UserModel> userModelMap = userModelList.stream().collect(Collectors.toMap(UserModel::getModelId, Function.identity()));
        List<ModelsDto> collect = modelsList.stream().map(models -> {
            //去数据库中匹配
            UserModel userModel = userModelMap.get(models.getModelId());
            if (Objects.isNull(userModel)){
                //去缓存中匹配
                ModelsDto dto = cacheModelsDto.get(models.getModelName());
                if (Objects.isNull(dto)){
                    throw new OpenAIRequestException(OpenAIErrorEnums.ERROR_312);
                }
                return dto;
            }
            ModelsDto modelsDto = BeanUtils.convert(models, ModelsDto.class);
            modelsDto.setInputMoney(userModel.getInputMoney());
            modelsDto.setMagnification(userModel.getMagnification());
            modelsDto.setOutputMoney(userModel.getOutputMoney());
            return modelsDto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Boolean checkModels(String models) {
        try {
            List<Long> modelIds = Arrays.stream(models.split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            Set<Long> modelsDtoList = getMyModels().stream()
                    .map(ModelsDto::getModelId)
                    .collect(Collectors.toSet());
            return modelIds.stream().allMatch(modelsDtoList::contains);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("输入的模型ID包含非法字符，无法转换为Long类型", e);
        }
    }

    @Override
    public ModelsDto getModelByName(String name,Long userId) {
        //先去缓存中根据用户id去查找
        Map<String, ModelsDto> cacheMap = redisService.getCacheMap(RedisConstants.CACHE_MODEL_USER + userId);
        //存在直接返回，
        if (CollectionUtil.isEmpty(cacheMap)){
            //从数据中查询，然后set进去。
            LambdaQueryWrapper<Models> queryWrapper = new LambdaQueryWrapper<Models>().in(BaseEntity::getCreateUserId, 1L, userId).eq(BaseEntity::getDelFlag, false);
            List<ModelsDto> modelsDtoList = convert(list(queryWrapper), userId);
            cacheMap = modelsDtoList.stream().collect(Collectors.toMap(ModelsDto::getModelName, Function.identity()));
            redisService.setCacheMap(RedisConstants.CACHE_MODEL_USER+userId,cacheMap);
        }
        return cacheMap.get(name);
    }

    public  ModelsDto getDefaultModel(){
        ModelsDto dto = new ModelsDto();
        dto.setModelName("gpt-3.5-turbo");
        dto.setMagnification(BigDecimal.ONE);
        dto.setInputMoney(new BigDecimal("0.001"));
        dto.setOutputMoney(new BigDecimal("0.002"));
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean createModel(ModelsDto modelsDto) {
        Models models = BeanUtils.convert(modelsDto, Models.class);
        save(models);
        UserModel userModel = new UserModel();
        userModel.setModelId(models.getModelId());
        userModel.setUserId(StpUtil.getLoginIdAsLong());
        userModel.setOutputMoney(modelsDto.getOutputMoney());
        userModel.setInputMoney(modelsDto.getInputMoney());
        iUserModelService.save(userModel);
        return true;
    }

    @Override
    public Page<ModelsDto> queryList(ModelsReqDto reqDto) {
        LambdaQueryWrapper<Models> queryWrapper = new LambdaQueryWrapper<Models>()
                .like(StringUtils.hasText(reqDto.getModelsName()), Models::getModelName, reqDto.getModelsName())
                .eq(StringUtils.hasText(reqDto.getModelsType()), Models::getModelType, reqDto.getModelsType())
                .in(BaseEntity::getCreateUserId, 1L, StpUtil.getLoginIdAsLong())
                .eq(BaseEntity::getDelFlag, false);
        Page<Models> page = page(reqDto.startPage(), queryWrapper);
        List<ModelsDto> convert = convert(page.getRecords(), StpUtil.getLoginIdAsLong());
        Page<ModelsDto> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(convert);
        return resultPage;
    }


}
