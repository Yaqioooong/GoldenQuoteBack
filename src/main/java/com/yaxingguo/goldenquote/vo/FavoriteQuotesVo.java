package com.yaxingguo.goldenquote.vo;

import com.yaxingguo.goldenquote.entity.Quotes;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteQuotesVo extends Quotes {
    private LocalDateTime addTime;
}
