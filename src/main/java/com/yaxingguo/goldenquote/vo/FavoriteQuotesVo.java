package com.yaxingguo.goldenquote.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yaxingguo.goldenquote.entity.Quotes;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteQuotesVo {

    /**
     * 句子ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书籍ID
     */
    private Integer bookId;

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 出自章节
     */
    private String srcChapter;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addTime;
}
