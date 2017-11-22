package com.tianqian.self.config.aop;

import com.alibaba.fastjson.JSON;
import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BusinessException;
import com.tianqian.self.config.kafka.OperateLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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
    private RedisTemplate<String, Object> redisTemplate;

    private ThreadLocal<Boolean> booleanThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.tianqian.self.controller..*.*(..))")
    public void resubmitValidation(){}

    @Before("resubmitValidation()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object target = joinPoint.getTarget();
        String params = JSON.toJSONString(args);
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
            ValueOperations<String, Object> value = redisTemplate.opsForValue();
            Long result = value.increment(params, 1);
            // 判断返回值是否大于1，如果是则重复提交
            if(result > 1) {
                throw new BusinessException(BaseCodeEnum.RESUBMIT.getMessage(),BaseCodeEnum.RESUBMIT.getIndex());
            }
            // 设置过期时间5秒
            redisTemplate.expire(params, 5, TimeUnit.SECONDS);
        }else{
            booleanThreadLocal.set(false);
        }
    }

    @After("resubmitValidation()")
    public void doAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        if(booleanThreadLocal.get()) {
            // 清除redis中key为入参的数据
            redisTemplate.delete(params);
        }
    }

}
