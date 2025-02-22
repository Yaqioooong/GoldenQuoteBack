package com.yaxingguo.goldenquote.mapper;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaxingguo.goldenquote.entity.UserFavorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaxingguo.goldenquote.vo.FavoriteQuotesVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户quotes收藏表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2025-02-22
 */
public interface UserFavoritesMapper extends BaseMapper<UserFavorites> {

    @Select("SELECT q.*,uf.add_time FROM t_quotes q " +
            "JOIN t_user_favorites uf ON q.id = uf.quote_id " +
            "WHERE uf.user_id = #{userId} " +
            "ORDER BY uf.add_time DESC " +
            "LIMIT #{offset}, #{pageSize}")
    List<FavoriteQuotesVo> queryFavoritesWithDetailsByUserId(@Param("userId") Integer userId,
                                                             @Param("offset") Integer offset,
                                                             @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM t_user_favorites WHERE user_id = #{userId}")
    Integer countFavorites(Integer userId);
}
