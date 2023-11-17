package com.tang.core.modules.channel.model.dto;

import com.tang.common.domain.BasePage;
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
public class ChannelsReqDto extends BasePage {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private Integer channelType;

    /**
     * 渠道所属的分组ID
     */
    private Long groupId;


}
