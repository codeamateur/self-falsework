package com.tianqian.self.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ConfigBean {
    /**
     * 系统ip
     */
    public static String systemIp;

    @Value("${system.ip}")
    public void setSystemIp(String systemIp) {
        ConfigBean.systemIp = systemIp;
    }
}
