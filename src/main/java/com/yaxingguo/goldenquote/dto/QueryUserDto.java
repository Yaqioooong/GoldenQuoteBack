package com.yaxingguo.goldenquote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserDto {
    private String username;
    private Integer id;
    private Integer page;
    private Integer pageSize;
    private Integer offset;
}
