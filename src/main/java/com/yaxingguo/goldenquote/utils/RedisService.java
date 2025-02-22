package com.yaxingguo.goldenquote.utils;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置键值对，并指定过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    public void setValueWithExpire(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value); // 设置键值对
        redisTemplate.expire(key, timeout, unit);    // 设置过期时间
    }

    // 新增方法到RedisService类中
    public <T> T getObject(String key, Class<T> clazz) {
        String json = redisTemplate.opsForValue().get(key);
        return json != null ? JSON.parseObject(json, clazz) : null;
    }

    // 可选：添加对象序列化存储方法
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    // 可选：带过期时间的对象存储
    public void setObjectWithExpire(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value), timeout, unit);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    public void increment(String key, int i) {
        redisTemplate.opsForValue().increment(key, i);
    }

    public void decrement(String key, int i) {
        redisTemplate.opsForValue().decrement(key, i);
    }

    public Set<String> getKeysByPattern(String pattern){
        return redisTemplate.keys(pattern);
    }


}