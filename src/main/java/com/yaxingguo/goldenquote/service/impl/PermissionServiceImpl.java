package com.yaxingguo.goldenquote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaxingguo.goldenquote.entity.Permission;
import com.yaxingguo.goldenquote.entity.RolePermission;
import com.yaxingguo.goldenquote.entity.UserRole;
import com.yaxingguo.goldenquote.mapper.PermissionMapper;
import com.yaxingguo.goldenquote.mapper.RolePermissionMapper;
import com.yaxingguo.goldenquote.mapper.UserRoleMapper;
import com.yaxingguo.goldenquote.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> queryPermissions(Object loginId) {

        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginId);
        UserRole userRole = userRoleMapper.selectOne(queryWrapper);
        QueryWrapper<RolePermission> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("role_id",userRole.getRoleId());
        List<Integer> permissionIds = rolePermissionMapper.selectList(queryWrapper2)
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        if (!permissionIds.isEmpty()){
            QueryWrapper<Permission> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.in("id",permissionIds);
            return permissionMapper.selectList(queryWrapper3)
                    .stream()
                    .map(Permission::getCode)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
