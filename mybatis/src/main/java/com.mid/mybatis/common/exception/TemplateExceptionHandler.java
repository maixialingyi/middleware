package com.mid.project.common.exception;

import com.mid.project.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class TemplateExceptionHandler {

	//系统兜底
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		log.error("Exception: ", e);
		return Result.fail();
	}

	//自定义异常
	@ExceptionHandler(BaseBizException.class)
	public Result handleAdapterException(BaseBizException e) {
		log.error("BaseBizException: ", e);
		return Result.fail(e.getBizCode().getCode(),e.getMessage());
	}

	//post @RequestBody   Content-Type = application/json json字符串 这个参数校验异常处理
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result HandleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
		FieldError fieldError = fieldErrorList.get(0);
		log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
		return Result.fail(BizCode.PARAMETER_VERIFICATION.getCode(),
				fieldError.getField()+fieldError.getDefaultMessage());
	}

	//post 不加@RequestBody  application/x-www-form-urlencoded json对象 参数校验异常处理
	@ExceptionHandler(BindException.class)
	public Result handleBindException(BindException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
		return Result.fail(BizCode.PARAMETER_VERIFICATION.getCode(),
				fieldError.getField()+fieldError.getDefaultMessage());
	}

}
