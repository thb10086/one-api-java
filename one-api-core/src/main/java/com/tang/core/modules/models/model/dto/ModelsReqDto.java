package com.tang.core.modules.models.model.dto;

import com.tang.common.domain.BasePage;
import lombok.Data;

@Data
public class ModelsReqDto extends BasePage {
    private String modelsName;
    private String modelsType;
}
