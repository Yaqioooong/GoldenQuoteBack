package com.yaxingguo.goldenquote.service.impl;

import com.yaxingguo.goldenquote.entity.LogRecord;
import com.yaxingguo.goldenquote.mapper.LogRecordMapper;
import com.yaxingguo.goldenquote.service.ILogRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志记录表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-02-14
 */
@Service
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecord> implements ILogRecordService {

}
