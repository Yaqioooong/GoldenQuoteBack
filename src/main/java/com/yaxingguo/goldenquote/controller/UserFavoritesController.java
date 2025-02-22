package com.yaxingguo.goldenquote.controller;


import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.entity.UserFavorites;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.service.IUserFavoritesService;
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
    public ResponseVo addFavorites(@RequestBody UserFavorites userFavorites){
        try{
            boolean b = userFavoritesService.addUserFavorites(userFavorites);
            return b?ResponseVo.success(b):ResponseVo.failure(ErrorConstants.ADD_FAVORITES_ERROR);
        }catch (BusinessException e){
            return ResponseVo.failure(e.getErr());
        }
    }
    @PostMapping("/remove")
    @ResponseBody
    @LogExec
    public ResponseVo removeFavorites(@RequestBody UserFavorites userFavorites){
        try{
            boolean b = userFavoritesService.removeUserFavorites(userFavorites);
            return b?ResponseVo.success(b):ResponseVo.failure(ErrorConstants.ADD_FAVORITES_ERROR);
        }catch (BusinessException e){
            return ResponseVo.failure(e.getErr());
        }
    }

    @GetMapping("/list")
    @ResponseBody
    @LogExec
    public ResponseVo listFavorites(@RequestParam(value = "userId" ,required = true) Integer uerId,
                                    @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        PageResult<FavoriteQuotesVo> res = userFavoritesService.queryUserFavorites(uerId,page,pageSize);
        return ResponseVo.success(res);
    }

}
