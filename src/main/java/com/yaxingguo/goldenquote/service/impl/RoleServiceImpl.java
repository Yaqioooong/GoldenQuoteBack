package com.yaxingguo.goldenquote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaxingguo.goldenquote.entity.Role;
import com.yaxingguo.goldenquote.entity.UserRole;
import com.yaxingguo.goldenquote.mapper.RoleMapper;
import com.yaxingguo.goldenquote.mapper.UserRoleMapper;
import com.yaxingguo.goldenquote.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> queryRoles(Object loginId) {

        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", loginId);
        UserRole userRole = userRoleMapper.selectOne(wrapper);
        if (userRole == null){
            return Collections.emptyList();
        }
        QueryWrapper<Role> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", userRole.getRoleId());
        return roleMapper.selectList(wrapper1)
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

    }
}
