package com.tang.security.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UsersLogonDto {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;
}
