package com.tianqian.self.config.kafka;

import java.util.Date;

public class OperateLog {
    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 请求类型
     */
    private String httpType;

    /**
     *  方法名
     */
    private String methodName;

    /**
     * 操作人员id
     */
    private Long opUserId;

    /**
     * 请求参数
     */
    private String reqArgs;

    /**
     * 返回参数
     */
    private String resArgs;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * 请求起始时间
     */
    private Date startTime;

    /**
     * 请求完成时间
     */
    private Date endTime;

    /**
     * 请求耗时
     */
    private long spendTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHttpType() {
        return httpType;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReqArgs() {
        return reqArgs;
    }

    public void setReqArgs(String reqArgs) {
        this.reqArgs = reqArgs;
    }

    public String getResArgs() {
        return resArgs;
    }

    public void setResArgs(String resArgs) {
        this.resArgs = resArgs;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }
}
