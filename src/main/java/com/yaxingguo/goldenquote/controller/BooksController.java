package com.yaxingguo.goldenquote.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.constants.ErrorConstants;
import com.yaxingguo.goldenquote.dto.QueryBookDto;
import com.yaxingguo.goldenquote.entity.Books;
import com.yaxingguo.goldenquote.service.IBooksService;
import com.yaxingguo.goldenquote.vo.ResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "BooksController",description = "书籍管理控制器")
public class BooksController {
    @Autowired
    IBooksService booksService;

    @GetMapping("/public/list")
    @ResponseBody
    @LogExec(requestMethod = "GET")
    @Operation(summary = "分页查询书籍列表")
    public ResponseVo getBooksList(@RequestParam(value = "bookName",required = false) String bookName,
                                   @RequestParam(value = "author",required = false) String author,
                                   @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){
        // 创建 Page 对象
        Page<Books> pageObject = new Page<>(page, pageSize);
        // 调用分页查询方法
        QueryBookDto dto = new QueryBookDto();
        dto.setBookName(bookName);
        dto.setAuthor(author);
        dto.setPage(page);
        dto.setPageSize(pageSize);
        Page<Books> resultPage = booksService.listBooks(dto);
        // 返回结果
        return ResponseVo.success(resultPage);
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public ResponseVo addBooks(@RequestBody Books books){
        boolean result = booksService.save(books);
        if (result){
            return ResponseVo.success("添加成功");
        }else {
            return ResponseVo.failure(ErrorConstants.ADD_BOOKS_ERROR);
        }
    }

    @PostMapping("/admin/update")
    @ResponseBody
    public ResponseVo updateBooks(@RequestBody Books books){
        boolean result = booksService.updateById(books);
        if (result){
            return ResponseVo.success("修改成功");
        }else {
            return ResponseVo.failure(ErrorConstants.UPDATE_BOOKS_ERROR);
        }
    }

    @PostMapping("/admin/delete")
    @ResponseBody
    public ResponseVo deleteBooks(@RequestBody Books books){
        boolean result = booksService.removeById(books);
        if (result){
            return ResponseVo.success("删除成功");
        }else {
            return ResponseVo.failure(ErrorConstants.DELETE_BOOKS_ERROR);
        }
    }
}