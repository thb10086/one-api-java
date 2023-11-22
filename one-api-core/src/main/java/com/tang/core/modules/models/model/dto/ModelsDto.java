package com.tang.core.modules.models.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@Getter
@Setter
public class ModelsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long modelId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型列表
     */
    private String modelType;

    /**
     * 输入价格
     */
    private BigDecimal inputMoney;

    /**
     * 输出价格
     */
    private BigDecimal outputMoney;

}
