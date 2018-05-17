package com.ls.brand.constants;

public enum ResultCode {

	SUCCESS(200,"成功"),//成功
    ERROR(500,"系统异常"),//失败
	
	//user模块   业务异常代码1xxx
	
	PARAM_NOT_NULL(1001,"参数不能为空"),
	USER_IS_NULL(1002,"用户为空");
	

	private final int code;

    private final String message;

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
