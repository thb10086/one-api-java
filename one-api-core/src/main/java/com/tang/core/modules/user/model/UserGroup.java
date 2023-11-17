package com.tang.core.modules.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-11-13
 */
@Getter
@Setter
@TableName("user_group")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long groupId;


}
