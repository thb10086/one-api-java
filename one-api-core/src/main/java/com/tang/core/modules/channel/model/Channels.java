package com.tang.core.modules.channel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.tang.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@Getter
@Setter
public class Channels extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private Integer channelType;

    /**
     * 策略
     */
    private Integer strategy;


    /**
     * 是否禁用
     */
    private Boolean isDisabled;


    /**
     * 代理地址
     */
    private String proxyAddress;


}
