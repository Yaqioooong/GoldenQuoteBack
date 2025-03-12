package com.yaxingguo.goldenquote.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryUserVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 角色
     */
    private String role;
    /**
     * 状态
     */
    private Integer status;
}
