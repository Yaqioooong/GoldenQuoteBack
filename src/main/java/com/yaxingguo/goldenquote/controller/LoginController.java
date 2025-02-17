package com.yaxingguo.goldenquote.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.dto.LoginDto;
import com.yaxingguo.goldenquote.dto.RegisterDto;
import com.yaxingguo.goldenquote.entity.User;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.service.IUserService;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    @LogExec(requestMethod = "POST")
    public ResponseVo login(@RequestBody LoginDto dto) {
        if (StringUtils.isAnyBlank(dto.getUsername(), dto.getPassword())) {
            throw new BusinessException(ErrorConstants.LOGIN_INFO_ERROR);
        }

        try {
            User authenticated = userService.authenticate(dto);
            if (authenticated == null){
                return ResponseVo.failure(ErrorConstants.LOGIN_INFO_ERROR);
            }else {
                StpUtil.login(authenticated.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                return ResponseVo.success(tokenInfo);
            }
        } catch (BusinessException e) {
            return ResponseVo.failure(e.getErr());
        } catch (Exception e) {
            return ResponseVo.failure(ErrorConstants.LOGIN_DECRYPT_ERROR);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseVo register(@RequestBody RegisterDto user) {
        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            throw new BusinessException(ErrorConstants.LOGIN_INFO_ERROR);
        }
        try {
            User authenticated = userService.register(user);
            if (authenticated == null){
                return ResponseVo.failure(ErrorConstants.LOGIN_INFO_ERROR);
            }else {
                return ResponseVo.success("注册成功");
            }
        }catch (BusinessException e){
            return ResponseVo.failure(e.getErr());
        }
    }


}