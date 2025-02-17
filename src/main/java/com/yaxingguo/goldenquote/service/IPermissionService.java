package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
public interface IPermissionService extends IService<Permission> {

    List<String> queryPermissions(Object loginId);
}
