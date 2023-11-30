package com.tang.security.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tang.common.constant.Constants;
import com.tang.common.domain.R;
import com.tang.common.exception.ServiceException;
import com.tang.common.utils.BeanUtils;
import com.tang.common.utils.RSAUtil;
import com.tang.core.modules.user.dto.UsersDto;
import com.tang.core.modules.user.service.IUsersService;
import com.tang.security.model.UserLoginDto;
import com.tang.core.modules.user.model.Users;
import com.tang.security.model.UsersLogonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys")
public class LoginController {
    @Autowired
    IUsersService iUsersService;
    @PostMapping("/login")
    public R<String> login(@RequestBody @Validated UserLoginDto reqDto){
        Users users = iUsersService.getOne(new LambdaUpdateWrapper<Users>().eq(Users::getUsername, reqDto.getUsername()).eq(Users::getDelFlag, false));
        if (users==null){
            throw new ServiceException("账号不存在！", Constants.FAIL);
        }
        if (!users.getStatus()){
            throw new ServiceException("账号被封禁，请联系管理员！", Constants.FAIL);
        }
        if (!SaSecureUtil.sha256(RSAUtil.decrypt(reqDto.getPassword())).equals(users.getPassword())){
            throw new ServiceException("密码错误！", Constants.FAIL);
        }
        //登录
        StpUtil.login(users.getUserId(), SaLoginConfig.setExtra("user",users));
        //返回token
        return R.ok(StpUtil.getTokenValue());
    }

    @PostMapping("/logout")
    public R<Boolean> logout(){
        StpUtil.logout();
        return R.ok(true);
    }

    @GetMapping("/user/info")
    public R<UsersDto> info(String token){
        Object userId = StpUtil.getLoginIdByToken(token);
        return R.ok(iUsersService.getUserInfo(Long.valueOf(userId.toString())));
    }

    @PostMapping("/logon")
    public R<Boolean> logon(@RequestBody @Validated UsersLogonDto reqDto){
        Users user = iUsersService.getOne(new LambdaUpdateWrapper<Users>().eq(Users::getUsername, reqDto.getUsername()).eq(Users::getDelFlag, false));
        if (user!=null){
            throw new ServiceException("账号已经被注册！", Constants.FAIL);
        }
        if (!reqDto.getPassword().equals(reqDto.getConfirmPassword())){
            throw new ServiceException("确认密码不一致！", Constants.FAIL);
        }
        user = new Users();
        user.setUsername(reqDto.getUsername());
        user.setPassword(SaSecureUtil.sha256(reqDto.getPassword()));
        user.setStatus(true);
        iUsersService.save(user);
        return R.ok(true,"注册成功！");
    }



}
