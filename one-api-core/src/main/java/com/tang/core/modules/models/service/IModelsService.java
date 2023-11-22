package com.tang.core.modules.models.service;

import com.tang.core.modules.models.model.Models;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.core.modules.models.model.dto.ModelsDto;

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

    List<ModelsDto> getModels();

    Boolean checkModels(String models);

    ModelsDto getModelByName(String name);

}
