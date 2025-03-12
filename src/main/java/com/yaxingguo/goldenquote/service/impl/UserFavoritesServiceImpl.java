package com.yaxingguo.goldenquote.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.constants.RedisKeyConstants;
import com.yaxingguo.goldenquote.dto.PageInfoDto;
import com.yaxingguo.goldenquote.entity.UserFavorites;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.mapper.QuotesMapper;
import com.yaxingguo.goldenquote.mapper.UserFavoritesMapper;
import com.yaxingguo.goldenquote.service.IQuotesService;
import com.yaxingguo.goldenquote.service.IUserFavoritesService;
import com.yaxingguo.goldenquote.utils.PageUtil;
import com.yaxingguo.goldenquote.utils.RedisService;
import com.yaxingguo.goldenquote.vo.FavoriteQuotesVo;
import com.yaxingguo.goldenquote.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户quotes收藏表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-22
 */
@Service
public class UserFavoritesServiceImpl extends ServiceImpl<UserFavoritesMapper, UserFavorites> implements IUserFavoritesService {

    @Autowired
    private UserFavoritesMapper userFavoritesMapper;

    @Autowired
    private IQuotesService quotesService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private QuotesMapper quotesMapper;

    @Override
    @Transactional
    public boolean addUserFavorites(UserFavorites userFavorites) {
        QueryWrapper<UserFavorites> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userFavorites.getUserId());
        wrapper.eq("quote_id",userFavorites.getQuoteId());
        UserFavorites one = userFavoritesMapper.selectOne(wrapper);
        if (one!=null){
            throw new BusinessException(ErrorConstants.DUPLICATE_FAVORITE);
        }
        // 添加用户收藏绑定关系
        try{
            userFavoritesMapper.insert(userFavorites);
            // quoteId对应记录收藏数+1
            String key = RedisKeyConstants.QUOTE_FAVORITES+userFavorites.getQuoteId();
            if (redisService.hasKey(key)){
                redisService.increment(key,1);
            }else {
                redisService.setValue(key,"1");
            }
            return true;
        }catch (Exception e){
            throw new BusinessException(ErrorConstants.ADD_FAVORITES_ERROR);
        }
    }

    @Override
    public boolean removeUserFavorites(UserFavorites userFavorites) {
        QueryWrapper<UserFavorites> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userFavorites.getUserId());
        wrapper.eq("quote_id",userFavorites.getQuoteId());
        UserFavorites one = userFavoritesMapper.selectOne(wrapper);
        if (one==null){
            throw new BusinessException(ErrorConstants.NO_FAVORITE_RECORD);
        }
        try{
            userFavoritesMapper.delete(wrapper);
            // quoteId对应记录收藏数-1
            String key = RedisKeyConstants.QUOTE_FAVORITES+userFavorites.getQuoteId();
            if (redisService.hasKey(key)){
                redisService.decrement(key,1);
            }
            return true;
        }catch (Exception e){
            throw new BusinessException(ErrorConstants.ADD_FAVORITES_ERROR);
        }
    }

    @Override
    public PageResult<FavoriteQuotesVo> queryUserFavorites(Integer userId, Integer bookId, Integer page, Integer pageSize) {
        // 参数校验
        if (userId == null) {
            throw new BusinessException(ErrorConstants.PARAM_ERROR);
        }
        // 查询总数
        Integer total = userFavoritesMapper.countFavorites(userId);
        // 分页参数处理
        PageInfoDto pageInfo = PageUtil.getPageInfo(page, pageSize, total);
        List<FavoriteQuotesVo> favoriteQuotesVoPage = userFavoritesMapper.queryFavoritesWithDetailsByUserId(userId,bookId,pageInfo.getOffset(),pageSize);
        return new PageResult<>(page,pageSize,total,pageInfo.getTotalPages(),favoriteQuotesVoPage);
    }


}
