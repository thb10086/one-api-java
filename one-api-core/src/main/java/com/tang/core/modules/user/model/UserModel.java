package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
@TableName("user_model")
public class UserModel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_model_id", type = IdType.AUTO)
    private Long userModelId;

    private Long userId;

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 倍率
     */
    private BigDecimal magnification;

    /**
     * 输出金额
     */
    private BigDecimal outputMoney;

    /**
     * 输入金额
     */
    private BigDecimal inputMoney;


}
