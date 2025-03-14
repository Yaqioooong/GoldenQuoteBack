package com.yaxingguo.goldenquote.constants;

public enum ErrorConstants {

    SUCCESS("100001", "success"),
    ADD_BOOKS_ERROR("200001", "添加书籍异常"),
    UPDATE_BOOKS_ERROR("200002", "更新书籍异常"),
    DELETE_BOOKS_ERROR("200003", "删除书籍异常"),
    ADD_QUOTES_ERROR("300001", "添加句子异常"),
    UPDATE_QUOTES_ERROR("300002", "更新句子异常"),
    DELETE_QUOTES_ERROR("300003", "删除句子异常"),
    LOGIN_INFO_ERROR("LOGIN_ERR_001", "登录参数不合法，请重新输入。"),
    LOGIN_USER_NOT_FOND("LOGIN_ERR_002", "该用户不存在"),
    LOGIN_PASSWORD_ERROR("LOGIN_ERR_003", "密码错误"),
    LOGIN_DECRYPT_ERROR("LOGIN_ERR_004", "解密失败"),
    REGISTER_FAIL("REGISTER_ERR_002","注册失败" ),
    USER_EXIST("REGISTER_ERR_001","用户已存在" ),
    USER_NOT_EXIST("USER_ERR_001","用户不存在" ),
    ROLE_NOT_EXIST("ROLE_ERR_001","角色不存在" ),
    ADD_FAVORITES_ERROR("FAVORITE_ERR_001","添加收藏异常"),
    DUPLICATE_FAVORITE("FAVORITE_ERR_002","重复收藏"),
    NO_FAVORITE_RECORD("FAVORITE_ERR_003","无收藏记录"),
    PARAM_ERROR("PARAM_ERR", "请校验参数"),
    QUOTE_NOT_EXIST("QUOTE_ERR_001","quote不存在" ),
    USER_ALREADY_BANNED("USER_ERR_002","账号已被封禁,请勿重复操作"),
    USER_NOT_BANNED("USER_ERR_003","账号未被封禁，请勿重复操作"),
    ;

    private String code;
    private String msg;

    ErrorConstants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
