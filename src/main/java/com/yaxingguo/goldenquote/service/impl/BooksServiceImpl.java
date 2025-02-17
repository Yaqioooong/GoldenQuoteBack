package com.yaxingguo.goldenquote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaxingguo.goldenquote.dto.QueryBookDto;
import com.yaxingguo.goldenquote.entity.Books;
import com.yaxingguo.goldenquote.mapper.BooksMapper;
import com.yaxingguo.goldenquote.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {

    @Autowired
    private BooksMapper booksMapper;

    
    public Page<Books> listBooks(QueryBookDto dto) {
        Page<Books> page = new Page<>(dto.getPage(), dto.getPageSize());
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        if (dto.getAuthor() != null) {
            queryWrapper.like("author", dto.getAuthor());
        }
        if (dto.getBookName() != null) {
            queryWrapper.like("book_name", dto.getBookName());
        }

        return booksMapper.selectPage(page, queryWrapper);
    }
}
