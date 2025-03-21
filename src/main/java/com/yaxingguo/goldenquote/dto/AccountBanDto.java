package com.yaxingguo.goldenquote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBanDto {
    private Integer userId;
    @JsonProperty("isBan")
    private boolean isBan = true;

}
