package com.yaxingguo.goldenquote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryQuotesDto {
    private Integer bookId;
    private Integer page;
    private Integer pageSize;
}
