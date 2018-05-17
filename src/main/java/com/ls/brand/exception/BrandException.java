package com.ls.brand.exception;

import com.ls.brand.constants.ResultCode;

public class BrandException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6915320182431659157L;
	private int code;

    public BrandException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
