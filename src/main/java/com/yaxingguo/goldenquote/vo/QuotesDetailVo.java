package com.yaxingguo.goldenquote.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaxingguo.goldenquote.entity.Quotes;
import lombok.Data;

import java.util.List;

@Data
public class QuotesDetailVo {
    private Page<Quotes> quotesList;
    private String bookName;
    private String author;
}
