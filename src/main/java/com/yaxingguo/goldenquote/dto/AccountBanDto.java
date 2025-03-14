package com.yaxingguo.goldenquote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountBanDto {
    private Integer userId;
    @JsonProperty("isBan")
    private boolean isBan = true;

}
