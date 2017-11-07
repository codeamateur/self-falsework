package com.tianqian.self.common;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer code;

	public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    

    public BusinessException(String message,Integer code) {
		super(message);
		this.code = code;
	}

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
