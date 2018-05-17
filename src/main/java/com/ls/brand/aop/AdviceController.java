package com.ls.brand.aop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ls.brand.constants.ResultCode;
import com.ls.brand.dto.JSONResult;
import com.ls.brand.exception.BrandException;

@ControllerAdvice
public class AdviceController {
	
	private final static Logger logger=LoggerFactory.getLogger(AdviceController.class);

	/**
     * 处理实体字段校验不通过异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JSONResult<?> validationError(MethodArgumentNotValidException ex) {
//        logger.error("参数异常 : {}" , ex);
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append(error.getField() + ":" + error.getDefaultMessage()+"\n");
        }
        return JSONResult.error(builder.toString());
    }
    
    
    /**
     * 处理参数校验不通过异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public JSONResult<?> validationError(MethodArgumentTypeMismatchException ex) {
//        logger.error("参数异常 : {}" , ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getParameter().getParameterName() + "参数类型不匹配,类型应为:" + ex.getParameter().getParameterType());
        return JSONResult.error(builder.toString());
    }
	
	
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public JSONResult<?> handleApiException(Exception e) {
        if(e instanceof BrandException) {
        	BrandException exception= (BrandException)e;
            return JSONResult.error(exception.getCode(), exception.getMessage());
        }else {
            logger.error("系统异常:{}" , e);
            return JSONResult.error(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage());
        }
    }
    
}
