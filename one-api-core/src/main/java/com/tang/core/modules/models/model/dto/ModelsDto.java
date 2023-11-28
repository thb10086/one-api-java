package com.tang.core.modules.models.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
@AllArgsConstructor
@NoArgsConstructor
public class ModelsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long modelId;

    /**
     * 模型名称
     */
    @NotNull(message = "模型名称不能为空")
    private String modelName;

    /**
     * 模型类型
     */
    @NotNull(message = "模型类型不能为空")
    private String modelType;


    /**
     * 输出金额
     */
    @NotNull(message = "输出价格不能为空")
    private BigDecimal outputMoney;

    /**
     * 输入金额
     */
    @NotNull(message = "输入价格不能为空")
    private BigDecimal inputMoney;

    /**
     * 倍率
     */
    private BigDecimal magnification;

}
