package com.yaxingguo.goldenquote.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.dto.AccountBanDto;
import com.yaxingguo.goldenquote.dto.QueryUserDto;

import com.yaxingguo.goldenquote.dto.UpdateRoleDto;
import com.yaxingguo.goldenquote.entity.User;
import com.yaxingguo.goldenquote.entity.UserRole;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.service.IUserService;
import com.yaxingguo.goldenquote.vo.PageResult;
import com.yaxingguo.goldenquote.vo.QueryUserVo;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


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

    /**
     * 更改用户角色接口
     */
    @RequestMapping(value="/admin/updateRole",method = RequestMethod.POST)
    @LogExec(requestMethod = "POST")
    @ResponseBody
    @SaCheckPermission("user:edit")
    public ResponseVo updateRole(@RequestBody UserRole entity){
        ResponseVo res = new ResponseVo();
        try{
            boolean flag = userService.updateRole(entity);
            if (flag){
                res = ResponseVo.success("更新成功");
            }
        }catch (BusinessException e){
            res = ResponseVo.failure(e.getErr());
        }
        return res;
    }

    /**
     * 封禁/解封用户接口
     */
    @RequestMapping(value="/admin/ban",method = RequestMethod.POST)
    @LogExec(requestMethod = "POST")
    @ResponseBody
    @SaCheckPermission("user:edit")
    public ResponseVo updateStatus(@RequestBody AccountBanDto dto){
        ResponseVo res = new ResponseVo();
        try{
            boolean flag = userService.banUser(dto);
            if (flag){
                res = ResponseVo.success(dto.isBan()?"已封禁账号":"已解封账号");
            }
        }catch (BusinessException e){
            res = ResponseVo.failure(e.getErr());
        }
        return res;
    }

    /**
     * 用户注销接口
     */
    @RequestMapping(value = "/admin/logoff" ,method = RequestMethod.POST)
    @LogExec(requestMethod = "POST")
    @ResponseBody
//    @SaCheckPermission("user:edit")
    public ResponseVo logOff(@RequestBody User user){
        ResponseVo res = new ResponseVo();
        try{
            boolean flag = userService.logOff(user);
            if (flag){
                res = ResponseVo.success("注销成功");
            }
        }catch (BusinessException e){
            res = ResponseVo.failure(e.getErr());
        }
        return res;
    }


}
