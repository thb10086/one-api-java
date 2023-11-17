package com.tang.core.modules.channel.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 渠道关联模型表
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@Getter
@Setter
@TableName("channel_model")
public class ChannelModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道id
     */
    private Long channelId;

    /**
     * 模型id
     */
    private Long modelId;


}
