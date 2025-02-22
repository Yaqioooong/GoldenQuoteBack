package com.yaxingguo.goldenquote.service;



import com.yaxingguo.goldenquote.entity.UserFavorites;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.vo.FavoriteQuotesVo;
import com.yaxingguo.goldenquote.vo.PageResult;

/**
 * <p>
 * 用户quotes收藏表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-22
 */
public interface IUserFavoritesService extends IService<UserFavorites> {

    boolean addUserFavorites(UserFavorites userFavorites);

    boolean removeUserFavorites(UserFavorites userFavorites);

    PageResult<FavoriteQuotesVo> queryUserFavorites(Integer uerId, Integer page, Integer pageSize);
}
