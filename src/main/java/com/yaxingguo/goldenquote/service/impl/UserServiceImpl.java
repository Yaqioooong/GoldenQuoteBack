package com.yaxingguo.goldenquote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.dto.LoginDto;
import com.yaxingguo.goldenquote.dto.RegisterDto;
import com.yaxingguo.goldenquote.entity.User;
import com.yaxingguo.goldenquote.entity.UserRole;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.mapper.UserMapper;
import com.yaxingguo.goldenquote.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaxingguo.goldenquote.utils.AESUtil;
import com.yaxingguo.goldenquote.utils.PasswordUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${aes.secret}")
    private String AES_SECRET_KEY;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Override
    public User getUserInfo(User userLogin) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", userLogin.getUsername());
        userQueryWrapper.eq("password", userLogin.getPassword());
        return getOne(userQueryWrapper);
    }


    @SneakyThrows
    @Override
    public User authenticate(LoginDto dto) {
        if (dto == null || StringUtils.isBlank(dto.getUsername())) {
            throw new BusinessException(ErrorConstants.LOGIN_INFO_ERROR);
        }
        User user = getOne(new QueryWrapper<User>()
                .eq("username", dto.getUsername())
                .last("LIMIT 1"));
        if (user == null) {
            throw new BusinessException(ErrorConstants.LOGIN_USER_NOT_FOND);
        }

        String rawPassword = AESUtil.decrypt(dto.getPassword(), AES_SECRET_KEY);
        if (PasswordUtil.checkPassword(rawPassword, user.getPassword())) {
            return user;
        } else {
            throw new BusinessException(ErrorConstants.LOGIN_PASSWORD_ERROR);
        }

    }

    @SneakyThrows
    @Override
    @Transactional
    public User register(RegisterDto dto) {
        // 校验该用户是否已注册，防止重复注册
        if (getOne(new QueryWrapper<User>().eq("username", dto.getUsername())) != null) {
            throw new BusinessException(ErrorConstants.USER_EXIST);
        }
        //执行注册逻辑
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(PasswordUtil.hashPassword(AESUtil.decrypt(dto.getPassword(),AES_SECRET_KEY)));
        user.setRealName(dto.getRealName());
        user.setStatus(1);
        userMapper.insert(user);
        if (user.getId() <= 0) {
            throw new BusinessException(ErrorConstants.REGISTER_FAIL);
        }
        // 设置默认角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(4);
        userRoleService.save(userRole);
        return user;

    }
}