package com.tang.core.modules.channel.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class ChannelsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long channelId;


    /**
     * 渠道名称
     */
    @NotNull(message = "渠道名称不能为空！")
    private String channelName;

    /**
     * 渠道类型
     */
    @NotNull(message = "渠道类型不能为空")
    private Integer channelType;

    /**
     * 渠道所属的分组ID
     */
    @NotNull(message = "渠道分组不能为空")
    private String groupId;

    /**
     * 模型列表
     */
    @NotEmpty(message = "模型不能为空")
    private String models;

    /**
     * 策略
     */
    private Integer strategy;

    /**
     * 代理地址
     */
    private String proxyAddress;

    /**
     * api-keys
     */
    private String apiKeys;

    /**
     * api-key csv文件解析
     */
    private MultipartFile csvFile;

}
