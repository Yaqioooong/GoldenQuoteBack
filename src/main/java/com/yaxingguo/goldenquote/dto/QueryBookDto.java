package com.yaxingguo.goldenquote.dto;

import lombok.Data;

@Data
public class QueryBookDto {
    private String bookName;
    private String author;
    private int page;
    private int pageSize;
}
