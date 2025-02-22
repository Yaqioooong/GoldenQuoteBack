package com.yaxingguo.goldenquote.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yaxingguo.goldenquote.dto.QueryQuotesDto;
import com.yaxingguo.goldenquote.entity.Quotes;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
public interface QuotesMapper extends BaseMapper<Quotes> {

    List<Quotes> selectBySelective(QueryQuotesDto dto);

    int incrLikes(Integer quoteId);
}
