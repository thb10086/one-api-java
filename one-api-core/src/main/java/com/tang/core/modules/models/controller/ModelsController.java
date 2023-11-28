package com.tang.core.modules.models.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.domain.R;
import com.tang.core.modules.models.model.dto.ModelsDto;
import com.tang.core.modules.models.model.dto.ModelsReqDto;
import com.tang.core.modules.models.service.IModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@RestController
@RequestMapping("/models")
public class ModelsController {

    @Autowired
    private IModelsService iModelsService;

    @PostMapping("/createModels")
    public R<Boolean> createModel(@RequestBody @Validated ModelsDto modelsDto){
        return R.ok(iModelsService.createModel(modelsDto));
    }

    @PostMapping("/deleteModel")
    public R<Boolean> deleteModel(@RequestBody Long modelId){
        return R.ok(iModelsService.deleteModel(modelId));
    }

    @PostMapping("/updateModel")
    public R<Boolean> updateModel(@RequestBody @Validated ModelsDto modelsDto){
        return R.ok(iModelsService.updateModel(modelsDto));
    }

    @PostMapping("/queryList")
    public R<Page<ModelsDto>> queryList(@RequestBody ModelsReqDto reqDto){
        return R.ok(iModelsService.queryList(reqDto));
    }
}

