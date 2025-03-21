package com.yaxingguo.goldenquote.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaxingguo.goldenquote.entity.Quotes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotesDetailVo {
    private Page<Quotes> quotesList;
    private String bookName;
    private String author;
}
