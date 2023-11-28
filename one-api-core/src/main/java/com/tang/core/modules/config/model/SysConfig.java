package com.tang.core.modules.config.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-27
 */
@Getter
@Setter
@TableName("sys_config")
public class SysConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_config_id", type = IdType.AUTO)
    private Long sysConfigId;

    private String sysName;

    private String sysCode;

    private String sysValue;


}
