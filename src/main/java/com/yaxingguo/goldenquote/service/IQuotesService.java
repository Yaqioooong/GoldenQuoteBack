package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.dto.QueryQuotesDto;
import com.yaxingguo.goldenquote.entity.Quotes;
import com.yaxingguo.goldenquote.vo.DailyQuoteVo;
import com.yaxingguo.goldenquote.vo.QuotesDetailVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
public interface IQuotesService extends IService<Quotes> {

    Page<Quotes> queryByDto(QueryQuotesDto dto);

    DailyQuoteVo getDailyQuote();

    int updateLikes(Integer quoteId,Integer likes);
}
