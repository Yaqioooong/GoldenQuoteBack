package com.yaxingguo.goldenquote.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.yaxingguo.goldenquote.service.IPermissionService;
import com.yaxingguo.goldenquote.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissions = permissionService.queryPermissions(loginId);
        if (permissions != null) {
            return permissions;
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roles = roleService.queryRoles(loginId);
        if (roles != null) {
            return roles;
        }
        return Collections.emptyList();
    }
}
