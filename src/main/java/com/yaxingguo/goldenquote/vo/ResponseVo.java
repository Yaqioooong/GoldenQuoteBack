package com.yaxingguo.goldenquote.vo;

import com.yaxingguo.goldenquote.constants.ErrorConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
public class ResponseVo {
    // Getter 和 Setter 方法
    private boolean success;
    private String code;
    private String message;
    private Object data;

    // 无参构造函数
    public ResponseVo() {
    }

    // 有参构造函数
    public ResponseVo(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法用于快速创建成功响应
    public static ResponseVo success(Object data) {
        return new ResponseVo(true,"200", "Success", data);
    }

    // 静态方法用于快速创建失败响应
    public static ResponseVo failure(ErrorConstants e) {
        return new ResponseVo(false,e.getCode(), e.getMsg(), null);
    }

    public static ResponseVo failure(int i, String message) {
        return new ResponseVo(false,String.valueOf(i), message, null);
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
