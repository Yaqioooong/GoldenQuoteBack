package com.yaxingguo.goldenquote.constants;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountStatusEnum {
    NORMAL(1,"正常"),
    LOCKED(2,"封禁"),
    DELETED(3,"注销");
    ;
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    AccountStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(Integer code){
        return Arrays.stream(AccountStatusEnum.values()).anyMatch(e -> e.getCode().equals(code));
    }
}
