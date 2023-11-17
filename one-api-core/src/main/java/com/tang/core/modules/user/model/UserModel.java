package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 倍率
     */
    private BigDecimal magnification;


}
