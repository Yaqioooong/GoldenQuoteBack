package com.yaxingguo.goldenquote.schedule;

import com.yaxingguo.goldenquote.constants.RedisKeyConstants;
import com.yaxingguo.goldenquote.entity.Quotes;
import com.yaxingguo.goldenquote.service.IQuotesService;
import com.yaxingguo.goldenquote.utils.RedisService;
import com.yaxingguo.goldenquote.vo.DailyQuoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    @Scheduled(cron = "${app.schedule.every_10_minutes}")
    public void scheduleUpdateQuoteLikes(){
        Set<String> keys = redisService.getKeysByPattern(RedisKeyConstants.QUOTE_FAVORITES + "*");
        keys.forEach(key -> {
            String[] split = key.split("_");
            Integer quoteId = Integer.parseInt(split[split.length-1]);
            try {
                Integer likes = Integer.parseInt(redisService.getValue(key));
                quotesService.updateLikes(quoteId,likes);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }



}
