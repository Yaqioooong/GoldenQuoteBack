package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.dto.*;
import com.yaxingguo.goldenquote.entity.User;
import com.yaxingguo.goldenquote.entity.UserRole;
import com.yaxingguo.goldenquote.vo.PageResult;
import com.yaxingguo.goldenquote.vo.QueryUserVo;

import java.util.List;

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

    PageResult<QueryUserVo> listByConditions(QueryUserDto dto);

    boolean updateRole(UserRole entity);

    boolean banUser(AccountBanDto dto);
}
