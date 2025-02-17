package com.yaxingguo.goldenquote.vo;

import com.yaxingguo.goldenquote.entity.Quotes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyQuoteVo {
    private Quotes quotes;
    private String bookName;
    private String author;
}
