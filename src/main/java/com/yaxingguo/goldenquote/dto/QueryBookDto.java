package com.yaxingguo.goldenquote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryBookDto {
    private String bookName;
    private String author;
    private int page;
    private int pageSize;
}
