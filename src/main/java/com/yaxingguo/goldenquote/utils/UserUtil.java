package com.yaxingguo.goldenquote.utils;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;

public class UserUtil {

    public static Integer getLoginUserId(){
        int userId = 0;
        if (StpUtil.isLogin()){
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            Object loginId = tokenInfo.getLoginId();
            userId = Integer.parseInt(loginId.toString());
        }
        return userId;
    }
}
