package com.tianqian.self.common.base;

public enum BaseCodeEnum {
	SUCCESS(10000,"成功"),
	FAILURE(10001,"失败"),
	ERROR(10002,"系统异常");
	
	private int index;
	
	private String message;
	
	BaseCodeEnum(int index, String message) {
		this.index = index;
		this.message = message;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
