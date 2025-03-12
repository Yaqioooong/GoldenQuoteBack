package com.yaxingguo.goldenquote.utils;

import com.yaxingguo.goldenquote.dto.PageInfoDto;

public class PageUtil {
    public static PageInfoDto getPageInfo(Integer page, Integer pageSize, Integer total) {
        page = page < 1 ? 1 : page;
        pageSize = pageSize < 1 ? 10 : pageSize;
        if (pageSize > 100) pageSize = 100; // 防止过大分页
        // 计算分页参数
        int totalPages = (int) Math.ceil((double)total / pageSize);
        int offset = (page - 1) * pageSize;
        return new PageInfoDto(totalPages, offset);
    }
}
