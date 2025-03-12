package com.yaxingguo.goldenquote.dto;

import lombok.Data;

@Data
public class QueryUserDto {
    private String username;
    private Integer id;
    private Integer page;
    private Integer pageSize;
    private Integer offset;
}
