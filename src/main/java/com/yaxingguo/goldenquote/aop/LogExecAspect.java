package com.yaxingguo.goldenquote.aop;


import com.yaxingguo.goldenquote.annotation.LogExec;
import com.yaxingguo.goldenquote.entity.LogRecord;
import com.yaxingguo.goldenquote.service.ILogRecordService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogExecAspect {

    @Autowired
    private ILogRecordService logRecordService;

    @Around("@annotation(com.yaxingguo.goldenquote.annotation.LogExec)")
    public Object logExecRecord(ProceedingJoinPoint joinPoint){
        LogRecord logRecord = new LogRecord();
        Object result = new Object();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExec annotation = method.getAnnotation(LogExec.class);
        String name = annotation.value().isEmpty()? method.getName() : annotation.value();
        logRecord.setOperationTime(LocalDateTime.now());
        logRecord.setRequestMethod(annotation.requestMethod());
        // 日志表记录
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);
        logRecord.setParams(params);
        logRecord.setMethodName(name);
        try {
            long start = System.currentTimeMillis();
            result = joinPoint.proceed();
            logRecord.setDuration(Math.toIntExact(System.currentTimeMillis() - start));
            logRecord.setResult(result.toString());
        } catch (Throwable throwable) {
            logRecord.setExceptionInfo(String.valueOf(throwable));
        }
        logRecordService.save(logRecord);
        return result;
    }
}
