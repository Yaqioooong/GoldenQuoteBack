package com.yaxingguo.goldenquote.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.dto.QueryUserDto;

import com.yaxingguo.goldenquote.service.IUserService;
import com.yaxingguo.goldenquote.vo.PageResult;
import com.yaxingguo.goldenquote.vo.QueryUserVo;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2025-02-01
 */
@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户列表接口
     */
    @RequestMapping(value="/admin/list",method = RequestMethod.GET)
    @LogExec(requestMethod = "GET")
    @ResponseBody
    @SaCheckPermission("user:list")
    public ResponseVo list(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            @RequestParam(value = "userId",required = false,defaultValue = "0") Integer userId,
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize)
    {
        QueryUserDto dto = new QueryUserDto();
        if (!StringUtils.isBlank(username)){
            dto.setUsername(username);
        }
        if (userId == 0){
            dto.setId(userId);
        }
        dto.setPage(page);
        dto.setPageSize(pageSize);
        PageResult<QueryUserVo> users = userService.listByConditions(dto);
        return ResponseVo.success(users);
    }

}
