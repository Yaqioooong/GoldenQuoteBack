package com.yaxingguo.goldenquote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yaxingguo.goldenquote.dto.QueryBookDto;
import com.yaxingguo.goldenquote.entity.Books;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
public interface IBooksService extends IService<Books> {
    Page<Books> listBooks(QueryBookDto dto);
}
