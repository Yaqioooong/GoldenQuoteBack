package com.yaxingguo.goldenquote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2025-01-04
 */
@TableName("t_quotes")
public class Quotes implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 出自章节
     */
    private String srcChapter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getSrcChapter() {
        return srcChapter;
    }

    public void setSrcChapter(String srcChapter) {
        this.srcChapter = srcChapter;
    }

    @Override
    public String toString() {
        return "Quotes{" +
            "id = " + id +
            ", bookId = " + bookId +
            ", content = " + content +
            ", addTime = " + addTime +
            ", likes = " + likes +
            ", srcChapter = " + srcChapter +
        "}";
    }
}
