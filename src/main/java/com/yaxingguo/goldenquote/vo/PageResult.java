package com.yaxingguo.goldenquote.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalItems;
    private Integer totalPages;
    private List<T> items;
}
