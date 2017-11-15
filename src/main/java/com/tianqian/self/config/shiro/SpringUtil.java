package com.tianqian.self.config.shiro;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware,DisposableBean {

    public static  ApplicationContext applicationContext = null;

    @Override
    public void destroy() throws Exception {
        SpringUtil.applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }
}
