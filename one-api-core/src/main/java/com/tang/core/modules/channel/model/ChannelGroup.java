package com.tang.core.modules.channel.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("channel_group")
public class ChannelGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long channelId;

    private Long groupId;


}
