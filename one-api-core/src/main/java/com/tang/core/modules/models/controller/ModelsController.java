package com.tang.core.modules.models.controller;


import com.tang.common.domain.R;
import com.tang.core.modules.models.model.dto.ModelsDto;
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



}

