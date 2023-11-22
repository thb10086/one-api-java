package com.tang.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BaseEntity {
    private LocalDateTime createTime;

    /**
     * 创建者用户ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;

    /**
     * 删除标志
     */
    private Boolean delFlag;


    /**
     * 用户名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;

    /**
     * 版本号
     */
    private Integer version;
}
