package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.dto.LoginDto;
import com.yaxingguo.goldenquote.dto.RegisterDto;
import com.yaxingguo.goldenquote.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
public interface IUserService extends IService<User> {

    User getUserInfo(User userLogin);


    User authenticate(LoginDto dto);

    User register(RegisterDto user);
}
