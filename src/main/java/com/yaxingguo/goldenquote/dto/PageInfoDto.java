package com.yaxingguo.goldenquote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfoDto {
    private Integer totalPages;
    private Integer offset;
}
