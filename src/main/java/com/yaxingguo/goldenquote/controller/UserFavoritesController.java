package com.yaxingguo.goldenquote.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.entity.UserFavorites;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.service.IUserFavoritesService;
import com.yaxingguo.goldenquote.utils.UserUtil;
import com.yaxingguo.goldenquote.vo.FavoriteQuotesVo;
import com.yaxingguo.goldenquote.vo.PageResult;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户quotes收藏表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2025-02-22
 */
@Controller
@RequestMapping("/api/v1/userFavorites")
public class UserFavoritesController {

    @Autowired
    private IUserFavoritesService userFavoritesService;

    @PostMapping("/add")
    @ResponseBody
    @LogExec
    @SaCheckLogin
    public ResponseVo addFavorites(@RequestBody UserFavorites userFavorites){
        try{
            userFavorites.setUserId(UserUtil.getLoginUserId());
            boolean b = userFavoritesService.addUserFavorites(userFavorites);
            return b?ResponseVo.success(b):ResponseVo.failure(ErrorConstants.ADD_FAVORITES_ERROR);
        }catch (BusinessException e){
            return ResponseVo.failure(e.getErr());
        }
    }
    @PostMapping("/remove")
    @ResponseBody
    @LogExec
    @SaCheckLogin
    public ResponseVo removeFavorites(@RequestBody UserFavorites userFavorites){
        try{
            userFavorites.setUserId(UserUtil.getLoginUserId());
            boolean b = userFavoritesService.removeUserFavorites(userFavorites);
            return b?ResponseVo.success(b):ResponseVo.failure(ErrorConstants.ADD_FAVORITES_ERROR);
        }catch (BusinessException e){
            return ResponseVo.failure(e.getErr());
        }
    }

    @GetMapping("/list")
    @ResponseBody
    @LogExec
    public ResponseVo listFavorites(@RequestParam(value = "bookId",required = true,defaultValue = "0") Integer bookId,
                                    @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        Integer userId = UserUtil.getLoginUserId();
        PageResult<FavoriteQuotesVo> res = userFavoritesService.queryUserFavorites(userId,bookId,page,pageSize);
        return ResponseVo.success(res);
    }


}
