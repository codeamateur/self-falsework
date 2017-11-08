package com.tianqian.self.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;



public class LocalBindingErrorUtil {
	
	public static <T> BaseResult<T> handle(BindingResult binding,Class<T> clazz){
		String message = "数据有误";
		for (Object obj : binding.getAllErrors()) {
			if (obj instanceof FieldError) {
				FieldError fieldError = (FieldError) obj;
				message =fieldError.getDefaultMessage();
				break;
			}
		}
		return new BaseResult<T>(false,BaseCodeEnum.FAILURE.getIndex(),message);
	}
}
