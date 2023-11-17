package com.tang.core.modules.transfer.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tang.common.domain.BaseEntity;
import com.tang.common.domain.BasePage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 中转平台API密钥表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
public class TransferApiKeysReqDto extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秘钥名称
     */
    private String keyName;


}
