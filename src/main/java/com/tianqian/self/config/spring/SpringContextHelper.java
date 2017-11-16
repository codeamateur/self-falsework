package com.tianqian.self.config.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHelper implements ApplicationContextAware{

    private static  ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

}
