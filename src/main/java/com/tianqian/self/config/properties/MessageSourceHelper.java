package com.tianqian.self.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MessageSourceHelper {
	
	private static ResourceBundleMessageSource messageSource;

	public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		if(locale == null){
			locale = new Locale("zh");
		}
		String msg = messageSource.getMessage(code, args, defaultMessage,locale);
		
		return msg != null ? msg.trim() : msg;
	}
	public static String getMessage(String code) {
		String msg = getMessage(code,null,"",null);
		return msg != null ? msg.trim() : msg;
	}

	public static String getMessage(String code, Object[] args) {
		String msg = messageSource.getMessage(code, args, "",null);
		return msg != null ? msg.trim() : msg;
	}


	/**
	 * 初始化
	 */
	@Bean
	public ResourceBundleMessageSource init(){
		MessageSourceHelper.messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return MessageSourceHelper.messageSource;
	}
	
}
