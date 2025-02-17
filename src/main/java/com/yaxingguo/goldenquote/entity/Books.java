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
@TableName("t_books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 国家
     */
    private String country;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                ", country='" + country + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
