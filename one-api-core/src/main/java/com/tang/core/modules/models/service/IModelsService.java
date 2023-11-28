package com.tang.core.modules.models.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.core.modules.models.model.Models;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.models.model.dto.ModelsReqDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
public interface IModelsService extends IService<Models> {

    List<ModelsDto> getMyModels();

    Boolean checkModels(String models);

    ModelsDto getModelByName(String name,Long userId);

    Boolean createModel(ModelsDto modelsDto);

    Boolean deleteModel(Long modelId);

    Boolean updateModel(ModelsDto modelsDto);

    Page<ModelsDto> queryList(ModelsReqDto reqDto);

    //默认模型
    ModelsDto getDefaultModel();

}
