package com.tianqian.self.config.aop;

import com.alibaba.fastjson.JSON;
import com.tianqian.self.config.kafka.OperateLog;
import com.tianqian.self.model.entity.user.SysUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private ThreadLocal<OperateLog> logThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.tianqian.self.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        OperateLog log = logThreadLocal.get();
        if(log == null){
            log = new OperateLog();
        }
        log.setStartTime(new Date());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.setUrl(request.getRequestURL().toString());
        log.setHttpType(request.getMethod());
        log.setIp(getIpAddr(request));
        log.setMethodName(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setReqArgs(getRequestArgs(joinPoint));
        logThreadLocal.set(log);
    }

    @AfterReturning(returning = "returnValue", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object returnValue) throws Throwable {
        handleLog(joinPoint, returnValue,null);
    }

    @AfterThrowing(pointcut = "webLog()",throwing="e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, null,e);
    }

    /**
     * 日志记录
     * @param joinPoint
     * @param retValue
     * @param e
     */
    public  void handleLog(JoinPoint joinPoint,Object retValue,Exception e) {
        OperateLog log = logThreadLocal.get();
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            if(user != null){
                log.setOpUserId(user.getId());
            }
        //获取响应参数
        if (e != null) {
            Writer wr = new StringWriter();
            PrintWriter pWriter = new PrintWriter(wr);
            e.printStackTrace(pWriter);
            log.setExceptionMsg(wr.toString());
        }
        if(retValue!=null){
            log.setResArgs(JSON.toJSONString(retValue));
        }
        Date endTime = new Date();
        log.setEndTime(endTime);
        log.setSpendTime(log.getEndTime().getTime()-log.getStartTime().getTime());
        kafkaTemplate.send("mylog_topic", JSON.toJSONString(log));

    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 接口参数，排除掉HttpServletRequest、HttpServletResponse、BindingResult
     * @param joinPoint
     * @return
     */
    public String getRequestArgs(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length>0){
            List<Object> lists = new ArrayList<Object>(Arrays.asList(args));
            for(Iterator<Object> iterator = lists.iterator(); iterator.hasNext();){
                Object obj = iterator.next();
                if(obj instanceof HttpServletRequest || obj instanceof HttpServletResponse || obj instanceof BindingResult){
                    iterator.remove();
                }
            }
            return JSON.toJSONString(lists);
        }
        return "";
    }
}

