package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
public interface IRoleService extends IService<Role> {

    List<String> queryRoles(Object loginId);
}
