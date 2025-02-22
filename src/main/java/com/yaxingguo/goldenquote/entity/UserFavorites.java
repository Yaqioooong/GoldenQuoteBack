package com.yaxingguo.goldenquote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户quotes收藏表
 * </p>
 *
 * @author baomidou
 * @since 2025-02-22
 */
@TableName("t_user_favorites")
public class UserFavorites implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * favorite_id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * quote id
     */
    private Integer quoteId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "UserFavorites{" +
            "id = " + id +
            ", userId = " + userId +
            ", quoteId = " + quoteId +
            ", addTime = " + addTime +
        "}";
    }
}
