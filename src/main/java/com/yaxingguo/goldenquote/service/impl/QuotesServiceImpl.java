package com.yaxingguo.goldenquote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.constants.RedisKeyConstants;
import com.yaxingguo.goldenquote.dto.QueryQuotesDto;
import com.yaxingguo.goldenquote.entity.Books;
import com.yaxingguo.goldenquote.entity.Quotes;
import com.yaxingguo.goldenquote.exception.BusinessException;
import com.yaxingguo.goldenquote.mapper.BooksMapper;
import com.yaxingguo.goldenquote.mapper.QuotesMapper;
import com.yaxingguo.goldenquote.service.IQuotesService;
import com.yaxingguo.goldenquote.utils.RedisService;
import com.yaxingguo.goldenquote.vo.DailyQuoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
@Service
public class QuotesServiceImpl extends ServiceImpl<QuotesMapper, Quotes> implements IQuotesService {

    @Autowired
    private QuotesMapper quotesMapper;

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Page<Quotes> queryByDto(QueryQuotesDto dto) {
        Page<Quotes> page = new Page<>(dto.getPage(), dto.getPageSize());
        QueryWrapper<Quotes> wrapper = new QueryWrapper<>();
        if (dto.getBookId()!=null){
            wrapper.eq("book_id",dto.getBookId());
        }
        return quotesMapper.selectPage(page,wrapper);
    }

    @Override
    public DailyQuoteVo getDailyQuote() {
        DailyQuoteVo vo = redisService.getObject(RedisKeyConstants.DAILY_QUOTE, DailyQuoteVo.class);
        if (vo!=null){
            return vo;
        }
        DailyQuoteVo dailyQuoteVo = new DailyQuoteVo();
        QueryWrapper<Quotes> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("rand()").last("limit 1");
        Quotes quotes = quotesMapper.selectOne(wrapper);
        Integer bookId = quotes.getBookId();
        Books books = booksMapper.selectById(bookId);
        dailyQuoteVo.setQuotes(quotes);
        dailyQuoteVo.setBookName(books.getBookName());
        dailyQuoteVo.setAuthor(books.getAuthor());
        redisService.setObject(RedisKeyConstants.DAILY_QUOTE,dailyQuoteVo);
        return dailyQuoteVo;
    }

    @Override
    public int updateLikes(Integer quoteId,Integer likes) {
        // 参数校验
        if (quoteId == null || likes == null) {
            throw new BusinessException(ErrorConstants.PARAM_ERROR);
        }

        Quotes quotes = quotesMapper.selectById(quoteId);
        if (quotes == null) {
            throw new BusinessException(ErrorConstants.QUOTE_NOT_EXIST);
        }

        quotes.setLikes(likes);
        return quotesMapper.updateById(quotes);
    }
}
