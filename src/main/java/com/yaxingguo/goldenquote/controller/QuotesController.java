package com.yaxingguo.goldenquote.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.dto.QueryQuotesDto;
import com.yaxingguo.goldenquote.entity.Books;
import com.yaxingguo.goldenquote.entity.Quotes;
import com.yaxingguo.goldenquote.service.IBooksService;
import com.yaxingguo.goldenquote.service.IQuotesService;
import com.yaxingguo.goldenquote.vo.DailyQuoteVo;
import com.yaxingguo.goldenquote.vo.QuotesDetailVo;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
@Controller
@RequestMapping("/api/v1/quotes")
public class QuotesController {

    @Autowired
    private IQuotesService quotesService;

    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/public/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo list(@RequestParam(value = "bookId" ,required = true) Integer bookId,
                           @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) {
        QuotesDetailVo res = new QuotesDetailVo();
        QueryQuotesDto dto = new QueryQuotesDto(bookId, page, pageSize); 
        Page<Quotes> quotes = quotesService.queryByDto(dto);
        Books book = booksService.getById(dto.getBookId());
        res.setQuotesList(quotes);
        res.setBookName(book.getBookName());
        res.setAuthor(book.getAuthor());
        return ResponseVo.success(res);
    }

    @RequestMapping(value = "/public/daily",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo getDailyQuote(){
         DailyQuoteVo quote = quotesService.getDailyQuote();
        return ResponseVo.success(quote);
    }

    @RequestMapping(value = "/admin/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo add(@RequestBody Quotes quotes) {
        boolean result = quotesService.save(quotes);
        if (result) {
            return ResponseVo.success(null);
        }
        return ResponseVo.failure(ErrorConstants.ADD_QUOTES_ERROR);
    }

    @RequestMapping(value = "/admin/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo update(@RequestBody Quotes quotes) {
        boolean result = quotesService.updateById(quotes);
        if (result) {
            return ResponseVo.success(null);
        }
        return ResponseVo.failure(ErrorConstants.UPDATE_QUOTES_ERROR);
    }

    @RequestMapping(value = "/admin/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo delete(@RequestBody Quotes quotes) {
        boolean result = quotesService.removeById(quotes);
        if (result) {
            return ResponseVo.success(null);
        }
        return ResponseVo.failure(ErrorConstants.DELETE_QUOTES_ERROR);
    }


}
