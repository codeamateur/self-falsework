package com.tianqian.self.config.exception;

import com.tianqian.self.common.base.BaseCodeEnum;
import com.tianqian.self.common.base.BaseResult;
import com.tianqian.self.common.base.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(value =Exception.class)
    public BaseResult<String> handleException(HttpServletRequest req, Exception e) throws Exception {
    	logger.error(req.getRequestURI(), e);
		return new BaseResult<String>(false,BaseCodeEnum.ERROR.getIndex(),BaseCodeEnum.ERROR.getMessage());
    }
    
    @ExceptionHandler(value =BusinessException.class)
    public BaseResult<String> handleBusinessException(HttpServletRequest req, BusinessException e) throws Exception {
    	logger.error(req.getRequestURI(), e);
		return new BaseResult<String>(false,e.getCode(),e.getMessage());
    }
}
