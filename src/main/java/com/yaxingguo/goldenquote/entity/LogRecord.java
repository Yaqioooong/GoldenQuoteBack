package com.yaxingguo.goldenquote.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志记录表
 * </p>
 *
 * @author baomidou
 * @since 2025-02-14
 */
@TableName("t_log_record")
public class LogRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志记录的唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日志级别（如 INFO, ERROR）
     */
    private String logLevel;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 操作人（用户名或ID）
     */
    private String operator;

    /**
     * 操作类型（如登录、新增）
     */
    private String operationType;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数（JSON格式）
     */
    private String params;

    /**
     * 方法返回值（JSON格式）
     */
    private String result;

    /**
     * 异常信息（JSON格式）
     */
    private String exceptionInfo;

    /**
     * 操作人IP地址
     */
    private String ipAddress;

    /**
     * 请求方法（GET, POST等）
     */
    private String requestMethod;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
            "id = " + id +
            ", logLevel = " + logLevel +
            ", operationTime = " + operationTime +
            ", operator = " + operator +
            ", operationType = " + operationType +
            ", methodName = " + methodName +
            ", params = " + params +
            ", result = " + result +
            ", exceptionInfo = " + exceptionInfo +
            ", ipAddress = " + ipAddress +
            ", requestMethod = " + requestMethod +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", duration = " + duration +
        "}";
    }
}
