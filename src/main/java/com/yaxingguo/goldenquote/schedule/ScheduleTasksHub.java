package com.yaxingguo.goldenquote.schedule;

import com.yaxingguo.goldenquote.constants.RedisKeyConstants;
import com.yaxingguo.goldenquote.service.IQuotesService;
import com.yaxingguo.goldenquote.utils.RedisService;
import com.yaxingguo.goldenquote.vo.DailyQuoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasksHub {

    @Autowired
    private IQuotesService quotesService;

    @Autowired
    private RedisService redisService;

    @Scheduled(cron = "${app.schedule.daily_0_oclock}")
    public void scheduleRefreshDailyQuote(){

        DailyQuoteVo dailyQuote = quotesService.getDailyQuote();
        redisService.setObject(RedisKeyConstants.DAILY_QUOTE, dailyQuote);

    }

}
