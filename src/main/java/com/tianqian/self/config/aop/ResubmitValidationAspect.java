package com.tianqian.self.config.aop;

import com.alibaba.fastjson.JSON;
import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Order(1)
public class ResubmitValidationAspect {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(ResubmitValidationAspect.class);

    @Autowired
    private RedissonClient redisson;

    private ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();

    private final String redissonKey = "ResubmitValidation";

    @Pointcut("execution(public * com.tianqian.self.controller..*.*(..))")
    public void resubmitValidation(){}

    @Before("resubmitValidation()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object target = joinPoint.getTarget();
        String params = JSON.toJSONString(Arrays.toString(args));
        if(logger.isDebugEnabled()) {
            logger.debug("before methodName: {}", method.getName());
            logger.debug("before args: {}", params);
            logger.debug("before target: {}", target);
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if(RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod())){
            booleanThreadLocal.set(true);
            // 将入参作为key自增值存入redis
            RMapCache<String, Integer> map = redisson.getMapCache(redissonKey);
            //判断重复提交
            if(map.containsKey(params)){
                throw new BusinessException(BaseCodeEnum.RESUBMIT.getMessage(),BaseCodeEnum.RESUBMIT.getIndex());
            }
            map.put(params,1,5,TimeUnit.SECONDS);
        }else{
            booleanThreadLocal.set(false);
        }
    }

    @After("resubmitValidation()")
    public void doAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(Arrays.toString(args));
        if(booleanThreadLocal.get()) {
            // 清除redis中key为入参的数据
            RMapCache<String, Integer> map = redisson.getMapCache(redissonKey);
            map.remove(params);
        }
    }

}
