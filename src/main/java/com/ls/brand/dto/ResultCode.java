package com.ls.brand.dto;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200,"成功"),//成功
    FAIL(400,"失败"),//失败
    UNAUTHORIZED(401,"未认证"),//未认证（签名错误）
    NOT_FOUND(404,"不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器异常"),
    PARAMS_NOT_NULL(7001,"参数不能为空");

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
