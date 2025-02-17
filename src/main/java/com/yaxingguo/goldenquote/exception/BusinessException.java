package com.yaxingguo.goldenquote.exception;

import com.yaxingguo.goldenquote.constants.ErrorConstants;

public class BusinessException extends RuntimeException {
    // 建议添加错误码字段（按需）
    private String code;
    private ErrorConstants err;

    // 原有构造方法（保持兼容）
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorConstants err){
        super(err.getMsg());
        this.code = err.getCode();
        this.err = err;
    }

    // 推荐扩展：含错误码的构造方法（更利于接口错误分类）
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    // Getter 方法（按需添加）
    public String getCode() {
        return code;
    }

    public ErrorConstants getErr() {
        return err;
    }

    // 可重写 getMessage() 携带更多信息（可选）
    @Override
    public String getMessage() {
        return String.format("[%s] %s", code, super.getMessage());
    }
}
